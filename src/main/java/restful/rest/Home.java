package restful.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import restful.dto.UserDTO;
import restful.entities.Authority;
import restful.entities.HiddenMessage;
import restful.entities.User;
import restful.services.UserService;

@Controller
@RequestMapping("/api")
public class Home {

    @Autowired
    private UserService userService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/user/username")
    @ResponseBody
    public ResponseEntity<User> getPerson(@Valid @RequestBody UserDTO dto) {
        System.out.println(dto.getUsername());
       return ResponseEntity.ok(userService.findByUsername(dto.getUsername()));
    }
    @PostMapping("/user/id")
    public ResponseEntity<User> getPersonById(@Valid @RequestBody User dto) {
        System.out.println(dto.getId());
        return ResponseEntity.ok(userService.findById(dto.getId()));
    }
    
    @GetMapping(value = "/alluser")
    @ResponseBody
    public List<User> getAllUser(){
        return userService.findAll();
    }
    @PostMapping(value = "/register")
    public ResponseEntity<HiddenMessage> createUser(@Valid @RequestBody User user) {
        System.out.println(user);
        if(userService.findByUsername(user.getUsername())!=null ) {
            return new ResponseEntity<>(new HiddenMessage("Username existed!","failed"),  HttpStatus.BAD_REQUEST);
        }
        if(userService.findByEmail(user.getEmail())!=null ) {
            return new ResponseEntity<>(new HiddenMessage("Email existed!","failed"),  HttpStatus.BAD_REQUEST);
        }
        Set<Authority> auth = new HashSet<Authority>();
        auth.add(new Authority("ROLE_ADMIN"));
        auth.add(new Authority("ROLE_USER"));
        
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        
        user.setAuthorities(auth);
        
        userService.save(user);
        return ResponseEntity.ok(new HiddenMessage("Created!","Successfully"));
    }


}
