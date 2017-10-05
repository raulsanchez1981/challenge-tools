package challenge.services;

import challenge.entities.Character;
import challenge.exception.types.ChallengeServiceException;
import challenge.repositories.CharacterRepository;
import challenge.search.CharacterSearch;
import challenge.security.ControlAccessCharacter;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
@Service
@EnableConfigurationProperties
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    ErrorMessages errorMessages;

    @Override
    @ControlAccessCharacter
    public List<Character> obtainCharacters(String userName, CharacterSearch characterSearch){
        return this.characterRepository.obtainCharactersByCharacter(userName, characterSearch);
    }

    @Override
    @ControlAccessCharacter
    public Character obtainCharacterById(String userName, String id) {
        Character output = null;
        Character character = this.characterRepository.findOne(id);
        if (null!=character && (userName.equals(character.getUserCreation()) || null == character.getUserCreation())) {
            output = character;
        }
        return output;
    }

    @Override
    @ControlAccessCharacter
    public Character saveCharacter(String userName, Character character) {
        try {
            character.setId(null);
            character.setUserCreation(userName);
            return this.characterRepository.save(character);
        } catch (DuplicateKeyException e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.DUPLICATE_CHARACTER));
        }
    }

    @Override
    @ControlAccessCharacter
    public Character updateCharacter(String userName, Character characterNew) {
        Character characterOld = this.characterRepository.findOne(characterNew.getId());
        if (null == characterOld) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.CHARACTER_NOT_FOUND));
        }
        if (!userName.equals(characterOld.getUserCreation())){
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.NOT_CREATOR_UPD));
        }
        try {
            this.characterRepository.updateCharacter(characterNew);
            return this.characterRepository.findOne(characterNew.getId());
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.UPDATE_ERROR));
        }
    }

    @Override
    @ControlAccessCharacter
    public void deleteCharacter(String userName, String id) {
        Character characterOld = this.characterRepository.findOne(id);
        if (!userName.equals(characterOld.getUserCreation())){
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.NOT_CREATOR_DEL));
        }
        try {
            this.characterRepository.delete(id);
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.DELETE_ERROR));
        }
    }

}
