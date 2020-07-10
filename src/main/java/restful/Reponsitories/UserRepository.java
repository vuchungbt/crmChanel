package restful.Reponsitories;

import org.springframework.stereotype.Repository;
import restful.entities.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Override
    
    @Query(value = "{}", fields = "{'password':0}")
    List<User> findAll();
    
    User findOneById(String id);
    
    User findByEmail(String email);
    
    User findByUsername(String username);
    
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);
}
