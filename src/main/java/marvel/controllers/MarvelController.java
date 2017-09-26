package marvel.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cbougeno on 25/09/2017.
 */
@RestController
public class MarvelController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
