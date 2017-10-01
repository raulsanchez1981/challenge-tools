package challenge.services;

import challenge.entities.User;

import java.util.List;

/**
 * Created by rsanchpa on 27/09/2017.
 */
public interface UserService {

    List<User> obtainAllUsers(String userName);

    List<User> getUserByName(String userName, String name);

    User getUserByUserName(String userName);

    User addUser(String userName, User user);

    User updateUser(String userName, User user);

    void enableUser(String userName, String id);

    void disableUser(String userName, String id);

    boolean isActiveUser(String userName);

    boolean isAdminAndActiveUser(String userName);

}
