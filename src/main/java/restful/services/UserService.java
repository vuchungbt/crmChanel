package restful.services;

import java.util.List;
import restful.entities.User;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User findByEmail(String email);
    User findByUsername(String username);
    void save(User user);
//    void update(User user);
//    void delete(String id);
}
