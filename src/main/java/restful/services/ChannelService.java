package restful.services;

import java.util.List;
import restful.entities.Channel;

/**
 *
 * developer VuChung
 */
public interface ChannelService {
    
    List<Channel> findAll();
    Channel findById(String id);
    //List<Channel> findByAssignTo(String assignTo);
    //List<Channel> findByCategory(String category);
    void save(Channel channel);

}
