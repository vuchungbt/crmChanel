package restful.Reponsitories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import restful.entities.Channel;

/**
 *
 * developer VuChung
 */
@Repository
public interface ChannelRepository extends MongoRepository<Channel, String>  {
    
    @Override     
    List<Channel> findAll();
    
    @Query(value="{ 'assignTo.id' : ?0 }")
    List<Channel> findByAssignTo(String assignTo);
    
    Channel findOneById(String id);
    Channel findByChannelId(String channelId);
    //List<Channel> findByAssignTo(String assignTo);
    //List<Channel> findByCategory(String category);

}
