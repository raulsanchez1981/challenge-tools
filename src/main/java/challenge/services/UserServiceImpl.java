package challenge.services;

import challenge.entities.User;
import challenge.enums.Rol;
import challenge.exception.types.ChallengeControlAcessException;
import challenge.exception.types.ChallengeServiceException;
import challenge.repositories.UserRepository;
import challenge.security.ControlAccessUser;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rsanchpa on 28/09/2017.
 */
@Service
@EnableConfigurationProperties
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ErrorMessages errorMessages;

    @Override
    @ControlAccessUser
    public List<User> obtainAllUsers(String userName) {
        return this.userRepository.findAll();
    }

    @Override
    @ControlAccessUser
    public List<User> getUserByName(String userName, String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    @ControlAccessUser
    public User getUserByUserName(String userName) {
        return this.userRepository.findByUserName(userName);
    }

    @Override
    @ControlAccessUser
    public User addUser(String userName, User user) {
        return this.userRepository.save(user);
    }

    @Override
    @ControlAccessUser
    public User updateUser(String userName, User user) {
        return this.userRepository.save(user);
    }

    @Override
    @ControlAccessUser
    public void enableUser(String userName, String id) {
        User user = this.userRepository.findOne(id);
        user.setActive(true);
        this.userRepository.save(user);
    }

    @Override
    @ControlAccessUser
    public void disableUser(String userName, String id) {
        User user = this.userRepository.findOne(id);
        user.setActive(false);
        this.userRepository.save(user);
    }

    @Override
    public boolean isActiveUser(String userName) {
        User user = this.getUserByUserName(userName);
        if (null==user) {
            throw new ChallengeControlAcessException(errorMessages.getProperty(ErrorCodes.USER_NOT_EXIST));
        } else if (!user.getActive()) {
            throw new ChallengeControlAcessException(errorMessages.getProperty(ErrorCodes.USER_NOT_ACTIVE));
        }
        return true;
    }

    @Override
    public boolean isAdminAndActiveUser(String userName) {
        isActiveUser(userName);
        User user = this.getUserByUserName(userName);
        if (!Rol.ADMIN.getValue().equalsIgnoreCase(user.getRol())) {
            throw new ChallengeControlAcessException(errorMessages.getProperty(ErrorCodes.USER_NOT_ADMIN));
        }
        return true;
    }

}
