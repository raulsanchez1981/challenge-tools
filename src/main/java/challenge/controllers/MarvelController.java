package challenge.controllers;

import challenge.prueba.Marvel;
import challenge.prueba.MarvelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cbougeno on 25/09/2017.
 */
@RestController
public class MarvelController {

    @Autowired
    MarvelRepository marvelRepository;

    @RequestMapping("/marvel")
    public String index() {
        Marvel obj2 = new Marvel();
        obj2 = marvelRepository.save(obj2);

        return obj2.getId();
    }
}
