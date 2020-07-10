package restful.ServiceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restful.Reponsitories.TaskRepository;
import restful.entities.Task;
import restful.services.TaskService;

/**
 *
 * developer VuChung
 */
@Service
@Transactional
public class TaskServiceImp implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(String id) {
        return taskRepository.findOneById(id);
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }

}
