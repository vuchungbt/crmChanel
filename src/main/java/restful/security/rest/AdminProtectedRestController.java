package restful.security.rest;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restful.dto.ChannelDTO;
import restful.entities.Channel;
import restful.services.ChannelService;
import restful.services.UserService;

@RestController
@RequestMapping("/api")
public class AdminProtectedRestController {

    @Autowired
    private ChannelService channelService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/hiddenmessage")
    public ResponseEntity<HiddenMessage> getAdminProtectedGreeting(Authentication authentication) {
       System.out.println(authentication.getName());
      return ResponseEntity.ok(new HiddenMessage("this is a hidden message!"));
    }
   

   private static class HiddenMessage {

      private final String message;

      private HiddenMessage(String message) {
         this.message = message;
      }

      public String getMessage() {
         return message;
      }
   }

}
