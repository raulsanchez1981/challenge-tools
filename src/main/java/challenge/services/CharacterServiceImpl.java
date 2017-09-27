package challenge.services;

import challenge.entities.Character;
import challenge.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Character> obtainCharacters(Character character){
        return this.characterRepository.findAll();
    }

    @Override
    public Character obtainCharacterById(String id) {
        return this.characterRepository.findOne(id);
    }

    @Override
    public Character saveCharacter(Character character) {
        return this.characterRepository.save(character);
    }

    @Override
    public Character updateCharacter(Character characterNew) {
       return this.characterRepository.save(characterNew);
    }

    @Override
    public void deleteCharacter(String id) {
        this.characterRepository.delete(id);
    }

}
