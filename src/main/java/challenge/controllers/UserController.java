package challenge.controllers;

import challenge.entities.User;
import challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rsanchpa on 28/09/2017.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @RequestMapping(method= RequestMethod.GET, value="/{name}")
    public List<User> findUsersByName(@RequestParam String userName, @PathVariable String name)  {
        return this.userService.getUserByName(userName, name);
    }

    @RequestMapping(method= RequestMethod.GET, value="/{userName}")
    public User findUsersByUserName(@PathVariable String userName)  {
        return this.userService.getUserByUserName(userName);
    }

    @RequestMapping(method=RequestMethod.GET, value="/search")
    public List<User> findAllUsers(@RequestParam String userName) {
        return this.userService.obtainAllUsers(userName);
    }

    @RequestMapping(method=RequestMethod.POST, value="/")
    public User saveUsers(@RequestParam String userName, @RequestBody User user)  {
        return this.userService.addUser(userName, user);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/")
    public User updateUser(@RequestParam String userName, @RequestBody User user)  {
        return this.userService.updateUser(userName, user);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/enable")
    public void activateUser(@RequestParam String userName, @RequestParam String id)  {
        this.userService.enableUser(userName, id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/disable")
    public void deActivateUser(@RequestParam String userName, @RequestParam String id)  {
        this.userService.disableUser(userName, id);
    }
}
