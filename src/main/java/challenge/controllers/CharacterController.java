package challenge.controllers;

import challenge.entities.Character;
import challenge.exception.types.ChallengeControllerException;
import challenge.exception.types.ChallengeServiceException;
import challenge.search.CharacterSearch;
import challenge.services.CharacterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cbougeno on 25/09/2017.
 */
@Api(description = "Characters REST services")
@RestController
@RequestMapping("/character")
public class CharacterController {

    @Autowired
    CharacterService characterService;

    @ApiOperation(value = "Obtain a Character By Id",
        notes = "The **userName** is a required parameter, the user must exists and must be active.\n\n"
            + "\nThe following validations are applied too:"
            + "\n"
            + "\n- **Id** must be filled<br>",
        response = Character.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found")})
    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public Character findCharacterById(@RequestHeader String userName, @PathVariable String id)  {
        return this.characterService.obtainCharacterById(userName, id);
    }

    @ApiOperation(value = "Obtain a List of Characters",
        notes = "The **userName** is a required parameter, the user must exists and must be active.\n\n"
            + "\nThe search can be done by the following fields:"
            + "\n"
            + "\n- **alterEgo**<br>"
            + "\n- **description**<br>"
            + "\n- **name**<br>"
            + "\n- **powers**<br>")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 204, message = "No Content"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method=RequestMethod.POST, value="/search")
    public List<Character> findCharacters(@RequestHeader String userName, @RequestBody CharacterSearch characterSearch)  {
        List<Character> listCharacters;
        try {
            listCharacters = this.characterService.obtainCharacters(userName, characterSearch);
            if (listCharacters.isEmpty()) {
                listCharacters = null;
            }
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
        return listCharacters;
    }

    @ApiOperation(value = "Add a new Character to the Collection",
        notes = "You can create any Character you want. There is a limitation:\n" +
            "- You can't create two character with the same name (if the character already exist in the original list, you " +
            "can create another with the same name once, but only one. For example, Spider-Man exists in the original collection," +
            "you can create your own Spider-Man, but only one.\n\n" +
            "The **userName** is a required parameter, the user must exists and must be active.\n\n"
            + "\nThe following validations are applied too:"
            + "\n"
            + "\n- **name** must be filled<br>"
            + "\n- **alterEgo** must be filled<br>"
            + "\n- **description** must be filled<br>"
            + "\n- **birthDate**<br>"
            + "\n- **image** must be filled<br>"
            + "\n- **powers**<br>"
            + "\n- **strength** must be filled<br>")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method=RequestMethod.POST, value="/")
    public Character saveCharacters(@RequestHeader String userName, @Valid @RequestBody Character character, BindingResult bindingResult)  {
        buildErrorMessages(bindingResult);
        try {
            return this.characterService.saveCharacter(userName, character);
        }catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    @ApiOperation(value = "Update a Character from the Collection",
        notes = "You only can update your own Characters. For example, Spider-Man exists in the original collection," +
            "you can create your own Spider-Man, and update it, but you won't be able to update any data from the original Spider-Man.\n\n" +
            "The **userName** is a required parameter, the user must exists and must be active.\n\n"
            + "\nThe following validations are applied too:"
            + "\n"
            + "\n- **Id** must be filled<br>")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method=RequestMethod.PUT, value="/")
    public Character updateCharacter(@RequestHeader String userName, @RequestBody Character character)  {
        //buildErrorMessages(bindingResult);
        try {
            return this.characterService.updateCharacter(userName, character);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    @ApiOperation(value = "Delete a Character from the Collection",
        notes = "You only can delete your own Characters. For example, Spider-Man exists in the original collection," +
            "you can create your own Spider-Man, and delete it, but you won't be able to delete the original Spider-Man.\n\n" +
            "The **userName** is a required parameter, the user must exists and must be active.\n\n"
            + "\nThe following validations are applied too:"
            + "\n"
            + "\n- **Id** must be filled<br>")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "No Content", response = Void.class),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Internal Server Error")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
    public void deleteCharacter(@RequestHeader String userName, @PathVariable String id)  {
        try {
            this.characterService.deleteCharacter(userName, id);
        } catch (ChallengeServiceException e) {
            throw new ChallengeControllerException(e.getMessage());
        }
    }

    private void buildErrorMessages(BindingResult bindingResult) {
        final StringBuilder builder = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                builder.append(error.getDefaultMessage());
                builder.append(System.getProperty("line.separator"));
            }
            throw new ChallengeControllerException(builder.toString());
        }
    }
}
