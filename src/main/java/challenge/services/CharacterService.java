package challenge.services;

import challenge.entities.Character;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
public interface CharacterService {

    List<Character> obtainCharacters(Character character);

    Character obtainCharacterById(String id);

    Character saveCharacter(Character character);

    Character updateCharacter(Character character);

    void deleteCharacter(String id);
}
