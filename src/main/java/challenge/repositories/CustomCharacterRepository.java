package challenge.repositories;

import challenge.entities.Character;
import challenge.search.CharacterSearch;

import java.util.List;

/**
 * Created by rsanchpa on 27/09/2017.
 */
public interface CustomCharacterRepository {

    List<Character> obtainCharactersByCharacter(String userName, CharacterSearch characterSearch);

    void updateCharacter(Character character);
}
