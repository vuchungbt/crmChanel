package restful.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import restful.dto.ChannelDTO;

import restful.entities.Channel;
import restful.entities.HiddenMessage;
import restful.entities.User;
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
    private ChannelService channelService;
    
    @Autowired
    private UserService userService;
    
    
    @GetMapping(value = "/channel/all")
    @ResponseBody
    public List<Channel> getAllUser(){
        return channelService.findAll();
    }
    @PostMapping(value = "/channel/insert")
    public ResponseEntity<HiddenMessage> createUser(@Valid @RequestBody ChannelDTO channeldto) {
        Channel channel = new Channel();
        
        channel.setCategory(channeldto.getCategory());
        channel.setChannelID(channeldto.getChannelID());
        channel.setCreatedBy(userService.findById(channeldto.getCreatedBy()));
        channel.setDescription(channeldto.getDescription());
        channel.setName(channeldto.getName());
        channel.setOwner(userService.findById(channeldto.getCreatedBy()));
        channel.setSource(channeldto.getSource());
        
        channelService.save(channel);
        return ResponseEntity.ok(new HiddenMessage("Created!","Successfully"));
    }

}
