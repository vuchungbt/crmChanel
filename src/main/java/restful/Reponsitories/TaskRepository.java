package restful.Reponsitories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import restful.entities.Task;

/**
 *
 * developer VuChung
 */
public interface TaskRepository extends MongoRepository<Task, String>   {
    
    @Override
    List<Task> findAll();
    Task findOneById(String id);
    Task findByTaskId(String taskId);
}
