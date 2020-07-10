package restful.ServiceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import restful.Reponsitories.UserRepository;
import restful.entities.User;
import restful.services.UserService;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
       return userRepository.findOneById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
         return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

//    @Override
//    public void update(User user) {
//        userRepository.save(user);
//    }
//
//    @Override
//    public void delete(String id) {
//        userRepository.deleteById(id);
//    }
    
    
    
}
