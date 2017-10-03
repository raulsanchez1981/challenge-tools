package challenge.controllers;

import challenge.entities.Character;
import challenge.exception.types.ChallengeControllerException;
import challenge.exception.types.ChallengeServiceException;
import challenge.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cbougeno on 25/09/2017.
 */
@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public Character findCharacterById(@RequestParam String userName, @PathVariable String id)  {
        return this.characterService.obtainCharacterById(userName, id);
    }

    @RequestMapping(method=RequestMethod.POST, value="/search")
    public List<Character> findCharacters(@RequestParam String userName, @RequestBody Character character)  {
        List<Character> listCharacters;
        try {
            listCharacters = this.characterService.obtainCharacters(userName, character);
            if (listCharacters.isEmpty()) {
                listCharacters = null;
            }
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
        return listCharacters;
    }

    @RequestMapping(method=RequestMethod.POST, value="/")
    public Character saveCharacters(@RequestParam String userName, @RequestBody Character character)  {
        try {
            return this.characterService.saveCharacter(userName, character);
        }catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    @RequestMapping(method=RequestMethod.PUT, value="/")
    public Character updateCharacter(@RequestParam String userName, @RequestBody Character character)  {
        try {
            return this.characterService.updateCharacter(userName, character);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public void deleteCharacter(@RequestParam String userName, @PathVariable String id)  {
        try {
            this.characterService.deleteCharacter(userName, id);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }
}
