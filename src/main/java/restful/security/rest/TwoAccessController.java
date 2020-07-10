package restful.security.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restful.entities.User;
import restful.security.securetyservices.UserService;

@RestController
@RequestMapping("/api")
public class TwoAccessController {

   private final UserService userService;

   public TwoAccessController(UserService userService) {
      this.userService = userService;
   }

   @GetMapping("/mod")
   public ResponseEntity<User> getActualUser() {
      return ResponseEntity.ok(userService.getUserWithAuthorities().get());
   }
}