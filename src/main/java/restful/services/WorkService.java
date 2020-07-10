package restful.services;

import java.util.List;
import restful.entities.Work;

/**
 *
 * developer VuChung
 */
public interface WorkService {
    List<Work> findAll();
    Work findById(String id);
    //List<Channel> findByAssignTo(String assignTo);
    //List<Channel> findByCategory(String category);
    void save(Work work);

}
