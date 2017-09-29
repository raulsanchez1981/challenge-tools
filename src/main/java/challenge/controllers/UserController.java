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
    public List<User> findUsersByName(@PathVariable String name)  {
        return this.userService.getUserByName(name);
    }
    @RequestMapping(method= RequestMethod.GET, value="/{userName}")
    public User findUsersByUserName(@PathVariable String userName)  {
        return this.userService.getUserByUserName(userName);
    }

    @RequestMapping(method=RequestMethod.GET, value="/search")
    public List<User> findAllUsers() {
        return this.userService.obtainAllUsers();
    }

    @RequestMapping(method=RequestMethod.POST, value="/")
    public User saveUsers(@RequestBody User user)  {
        return this.userService.addUser(user);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/")
    public User updateUser(@RequestBody User user)  {
        return this.userService.updateUser(user);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/enable")
    public void activateUser(@RequestParam String id)  {
        this.userService.enableUser(id);
    }
    @RequestMapping(method=RequestMethod.PUT, value="/disable")
    public void deActivateUser(@RequestParam String id)  {
        this.userService.disableUser(id);
    }
}
