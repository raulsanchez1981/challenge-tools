package challenge.services;

import challenge.entities.User;
import challenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rsanchpa on 28/09/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> obtainAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> getUserByName(String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void enableUser(String id) {
        User user = this.userRepository.findOne(id);
        user.setActive(true);
        this.userRepository.save(user);
    }

    @Override
    public void disableUser(String id) {
        User user = this.userRepository.findOne(id);
        user.setActive(false);
        this.userRepository.save(user);
    }

    @Override
    public boolean isActiveUser(String userName) {
        boolean active = false;
        User user = this.getUserByUserName(userName);
        if (null!=user) {
            active = user.getActive();
        }
        return active;
    }

}
