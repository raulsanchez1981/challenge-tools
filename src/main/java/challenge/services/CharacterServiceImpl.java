package challenge.services;

import challenge.entities.Character;
import challenge.exception.types.ChallengeServiceException;
import challenge.repositories.CharacterRepository;
import challenge.security.ControlAccessUsers;
import challenge.utils.ErrorCodes;
import challenge.utils.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.util.MongoDbErrorCodes;
import org.springframework.http.HttpStatus;
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
        } catch (DuplicateKeyException e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.DUPLICATE_CHARACTER));
        }
    }

    @Override
    @ControlAccessUsers
    public Character updateCharacter(String userName, Character characterNew) {
        try {
            return this.characterRepository.save(characterNew);
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.UPDATE_ERROR));
        }
    }

    @Override
    @ControlAccessUsers
    public void deleteCharacter(String userName, String id) {
        try {
            this.characterRepository.delete(id);
        } catch (Exception e) {
            throw new ChallengeServiceException(errorMessages.getProperty(ErrorCodes.DELETE_ERROR));
        }
    }

}
