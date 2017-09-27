package challenge.controllers;

import challenge.entities.Marvel;
import challenge.repositories.MarvelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cbougeno on 25/09/2017.
 */
@RestController
public class MarvelController {

    @Autowired
    MarvelRepository marvelRepository;

    @RequestMapping(method= RequestMethod.POST, value="/marvel")
    public String index() {
        Marvel obj2 = new Marvel();
        obj2.setHero("ASD");
        //obj2.setPower("123");
        obj2 = marvelRepository.save(obj2);

        return obj2.getId();
    }
}
