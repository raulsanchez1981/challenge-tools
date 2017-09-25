package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/hola")
    public String indexHola() {
        return "Holaaaaaaaaaaaaaaaaaaaaaaaaaa from Spring Boot!";
    }

}
