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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import restful.dto.ChannelDTO;

import restful.entities.Channel;
import restful.entities.HiddenMessage;
import restful.entities.Task;
import restful.entities.User;
import restful.services.ChannelService;
import restful.services.TaskService;
import restful.services.UserService;

/**
 *
 * developer VuChung
 */
@RestController
@RequestMapping("/api/task")
public class TaskRestController {
    
    @Autowired
    private ChannelService channelService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping(value = "/all")
    @ResponseBody
    public List<Channel> getAllChannel(){
        return channelService.findAll();
    }
    
    @GetMapping(value = "/profile")
    @ResponseBody
    public ResponseEntity getProTask(@Valid @RequestBody ChannelDTO channel ,Authentication authentication ){
        
        if(authentication==null) {
            return new ResponseEntity<>(new HiddenMessage("Not found current user!","Failed"),  HttpStatus.BAD_REQUEST);
        }
        String username = authentication.getName();
        
        String id = userService.findByUsername(username).getId();
        System.out.println(channelService.findByAssignTo(id).size());
        return new ResponseEntity<>(new HiddenMessage("Access database!","Successfully",channelService.findByAssignTo(id),HttpStatus.OK.value()),  HttpStatus.OK);
    }
    @PostMapping(value = "/assign")
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
    @PostMapping(value = "/insert")
    public ResponseEntity<restful.entities.HiddenMessage> createTask(@Valid @RequestBody Task task ,Authentication authentication ) {
        int taskId_ = (int) (taskService.count() + 1000) ;
        String idChannel = task.getNotes();
        if(authentication==null) {
            return new ResponseEntity<>(new HiddenMessage("Created failed!","Failed"),  HttpStatus.BAD_REQUEST);
        }
        
        String username = authentication.getName();
        task.setCompletePercent(0);
        task.setTaskId(""+taskId_);
        task.setCreatedBy(userService.findByUsername(username));
        Set<User> asu = task.getAssignTo();
        asu.add(task.getCreatedBy());
        task.setAssignTo(asu);
        taskService.save(task);
        
        Channel newchangnel = channelService.findByChannelId(idChannel);
        
        if(newchangnel!=null){
            newchangnel.getTask().add(task);
            
            channelService.save(newchangnel);
            
            return ResponseEntity.ok(new HiddenMessage("Added Task success ","Successfully"));
        }
        return ResponseEntity.ok(new restful.entities.HiddenMessage("Created!","Successfully"));
    }


}
