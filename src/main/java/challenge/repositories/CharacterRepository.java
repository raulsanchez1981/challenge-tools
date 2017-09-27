package challenge.repositories;


import challenge.entities.Character;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
@Repository
public interface CharacterRepository extends MongoRepository<Character, String>, CustomCharacterRepository {

    List<Character> findByAlterEgoAndName(String alterEgo, String name);

}


