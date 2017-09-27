package challenge.controllers;

import challenge.entities.Character;
import challenge.services.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rsanchpa on 27/09/2017.
 */
@RestController
@RequestMapping("/power")
public class PowerController {

    @Autowired
    PowerService powerService;

    @RequestMapping(method= RequestMethod.GET, value="/search")
    public List<String> findCharacters()  {
        return this.powerService.obtainAllPowers();
    }
}
