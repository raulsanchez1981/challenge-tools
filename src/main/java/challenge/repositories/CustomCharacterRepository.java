package challenge.repositories;

import challenge.entities.Character;

import java.util.List;

/**
 * Created by rsanchpa on 27/09/2017.
 */
public interface CustomCharacterRepository {

    List<Character> obtainCharactersByCharacter(String userName, Character character);

    void updateCharacter(Character character);
}
