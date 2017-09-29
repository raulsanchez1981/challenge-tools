package challenge.services;

import challenge.entities.Character;
import challenge.exception.types.ChallengeServiceException;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */

public interface CharacterService {


    List<Character> obtainCharacters(String userName, Character character);

    Character obtainCharacterById(String userName, String id);

    Character saveCharacter(String userName, Character character);

    Character updateCharacter(String userName, Character character);

    void deleteCharacter(String userName, String id);
}
