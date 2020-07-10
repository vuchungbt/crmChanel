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
    @Query(value="{ 'createdBy.id' : ?0 ,'owner.id' : ?1,'assignTo.id' : ?2 ,'task.id' : ?3  }")        
    List<Channel> findAll();
    
    Channel findOneById(String id);
    //List<Channel> findByAssignTo(String assignTo);
    //List<Channel> findByCategory(String category);

}
