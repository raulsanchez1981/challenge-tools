package challenge.services;

import challenge.entities.Character;
import challenge.repositories.CharacterRepository;
import challenge.security.ControlAccessUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    @ControlAccessUsers
    public List<Character> obtainCharacters(String userName, Character character){
        return this.characterRepository.obtainCharactersByCharacter(character);
    }

    @Override
    @ControlAccessUsers
    public Character obtainCharacterById(String userName, String id) {
        return this.characterRepository.findOne(id);
    }

    @Override
    @ControlAccessUsers
    public Character saveCharacter(String userName, Character character) {
        try {
            return this.characterRepository.save(character);
        } catch (DuplicateKeyException ignored) {
            return null;
        }
    }

    @Override
    @ControlAccessUsers
    public Character updateCharacter(String userName, Character characterNew) {
       return this.characterRepository.save(characterNew);
    }

    @Override
    @ControlAccessUsers
    public void deleteCharacter(String userName, String id) {
        this.characterRepository.delete(id);
    }

}
