package restful.Reponsitories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import restful.entities.Work;


/**
 *
 * developer VuChung
 */
public interface WorkRepository extends MongoRepository<Work, String> {
    @Override
    List<Work> findAll();
    Work findOneById(String id);
}
