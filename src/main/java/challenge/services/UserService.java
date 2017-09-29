package challenge.services;

import challenge.entities.User;

import java.util.List;

/**
 * Created by rsanchpa on 27/09/2017.
 */
public interface UserService {

    List<User> obtainAllUsers();

    List<User> getUserByName(String name);

    User getUserByUserName(String userName);

    User addUser(User user);

    User updateUser(User user);

    void enableUser(String id);

    void disableUser(String id);

    boolean isActiveUser(String userName);

}
