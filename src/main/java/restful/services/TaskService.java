package restful.services;

import java.util.List;
import restful.entities.Task;

/**
 *
 * developer VuChung
 */
public interface TaskService {
    List<Task> findAll();
    Task findById(String id);
    //List<Channel> findByAssignTo(String assignTo);
    //List<Channel> findByCategory(String category);
    void save(Task task);
}
