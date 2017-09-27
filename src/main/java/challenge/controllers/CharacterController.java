package challenge.controllers;

import challenge.entities.Character;
import challenge.entities.Character;
import challenge.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cbougeno on 25/09/2017.
 */
@RestController
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @RequestMapping(method=RequestMethod.GET, value="/character/{id}")
    public Character findCharacterById(@PathVariable String id)  {
        return this.characterService.obtainCharacterById(id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/character/search")
    public List<Character> findCharacters(@RequestBody Character character)  {
        return this.characterService.obtainCharacters(character);
    }

    @RequestMapping(method=RequestMethod.POST, value="/character")
    public Character saveCharacters(@RequestBody Character character)  {
        return this.characterService.saveCharacter(character);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/character")
    public Character updateCharacter(@RequestBody Character character)  {
        return this.characterService.updateCharacter(character);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/character/{id}")
    public void deleteCharacter(@PathVariable String id)  {
        this.characterService.deleteCharacter(id);
    }
}
