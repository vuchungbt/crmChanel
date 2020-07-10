package restful.ServiceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restful.Reponsitories.WorkRepository;
import restful.entities.Work;
import restful.services.WorkService;

/**
 *
 * developer VuChung
 */
@Service
@Transactional
public class WorkServiceImp implements WorkService{

    @Autowired
    private WorkRepository workRepository;
    
    
    @Override
    public List<Work> findAll() {
       return workRepository.findAll();
    }

    @Override
    public Work findById(String id) {
        return workRepository.findOneById(id);
    }

    @Override
    public void save(Work work) {
        workRepository.save(work);
    }

}
