package challenge.repositories;


import challenge.entities.Character;
import challenge.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByName(String name);

    User findByUserName(String userName);
}


