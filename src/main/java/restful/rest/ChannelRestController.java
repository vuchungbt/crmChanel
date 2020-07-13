package restful.rest;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import restful.dto.ChannelDTO;

import restful.entities.Channel;
import restful.entities.HiddenMessage;
import restful.entities.Task;
import restful.services.ChannelService;
import restful.services.TaskService;
import restful.services.UserService;

/**
 *
 * developer VuChung
 */
@RestController
@RequestMapping("/api")
public class ChannelRestController {
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private ChannelService channelService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/channel/all")
    @ResponseBody
    public List<Channel> getAllChannel(){
        return channelService.findAll();
    }
    
    @GetMapping(value = "/channel/profile")
    @ResponseBody
    public ResponseEntity getProChannel(Authentication authentication ){
        
        if(authentication==null) {
            return new ResponseEntity<>(new HiddenMessage("Not found current user!","Failed"),  HttpStatus.BAD_REQUEST);
        }
        String username = authentication.getName();
        String id = userService.findByUsername(username).getId();
        //System.out.println(channelService.findByAssignTo(id).size());
        return new ResponseEntity<>(new HiddenMessage("Access database!","Successfully",channelService.findByAssignTo(id),HttpStatus.OK.value()),  HttpStatus.OK);
    }
    @GetMapping(value = "/channel/detail")
    @ResponseBody
    public ResponseEntity getDetailChannel(@RequestParam String channelId ){
        Channel detailChannel = channelService.findByChannelId(channelId);
        if(detailChannel!=null){
            return ResponseEntity.ok(detailChannel);
        }
        else {
            return new ResponseEntity<>(new HiddenMessage("Not found any Channel","Failed"),  HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(value = "/channel/assign")
    public ResponseEntity<HiddenMessage> createUser(@Valid @RequestBody ChannelDTO channel ) {
        Channel newchangnel = channelService.findByChannelId(channel.getChannelId());
        if(newchangnel!=null){
            newchangnel.getAssignTo().add(userService.findByUsername(channel.getAssign()));
            channelService.save(newchangnel);
            return ResponseEntity.ok(new HiddenMessage("Added Assign for username "+channel.getAssign(),"Successfully"));
        }
        else {
            return new ResponseEntity<>(new HiddenMessage("Not found Channel!","Failed"),  HttpStatus.BAD_REQUEST);
        }
        
    }
    
    @PostMapping(value = "/channel/addtask")
    public ResponseEntity addTask(@Valid @RequestBody Channel channel ) {
        Channel newchangnel = channelService.findByChannelId(channel.getChannelId());
        
        if(newchangnel!=null){
            Set<Task> newt = new HashSet<Task>();
            for (Task one : channel.getTask()) { 
                newt.add(taskService.findByTaskId(one.getTaskId()));
            } 
            if(newt.isEmpty()) {
                return new ResponseEntity<>(new HiddenMessage("Not found Task!","Failed"),  HttpStatus.BAD_REQUEST);
            }
            newchangnel.setTask(newt);
            
            channelService.save(newchangnel);
            
            return ResponseEntity.ok(new HiddenMessage("Added Task success ","Successfully"));
        }
        else {
            return new ResponseEntity<>(new HiddenMessage("Not found Channel!","Failed"),  HttpStatus.BAD_REQUEST);
        }
        
    }
    
     
    @PostMapping(value = "/channel/insert")
    public ResponseEntity<restful.entities.HiddenMessage> createUser(@Valid @RequestBody Channel channel ,Authentication authentication ) {
//        Channel channel = new Channel();
//        //System.out.println("Name:"+authentication.getName());
//        channel.setCategory(channeldto.getCategory());
//        channel.setChannelId(channeldto.getChannelId());
//        channel.setCreatedBy(userService.findByUsername(authentication.getName()));
//        channel.setDescription(channeldto.getDescription());
//        channel.setName(channeldto.getName());
//        channel.setOwner(userService.findByUsername(authentication.getName()));
//        channel.setSource(channeldto.getSource());
        //System.out.println("Change assign "+channel.getAssignTo().size());
        channelService.save(channel);
        return ResponseEntity.ok(new restful.entities.HiddenMessage("Created!","Successfully"));
    }


}
