package challenge.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping(method= RequestMethod.GET, value="/holaGET")
    public String methodGet(@PathVariable String owner) {
        return "Holaaaa GET";
    }

    @RequestMapping(method= RequestMethod.POST, value="/HolaPOST")
    public String methodPost(@RequestBody String prueba) {
        return "Hola POST";
    }

    @RequestMapping(method= RequestMethod.PUT, value="/holaPUT")
    public String methodPUT(@RequestBody String prueba) {
        return "Hola PUT";
    }

    @RequestMapping(method= RequestMethod.PATCH, value="/holaPATCH")
    public String methodPATCH(@RequestBody String prueba) {
        return "Hola PATCH";
    }

    @RequestMapping(method= RequestMethod.DELETE, value="/holaDELETE")
    public void methodDELETE(@RequestHeader String prueba) {
    }
}
