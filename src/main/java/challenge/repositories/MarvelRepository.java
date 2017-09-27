package challenge.repositories;


import challenge.entities.Marvel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by cbougeno on 26/09/2017.
 */
@Repository
public interface MarvelRepository extends MongoRepository<Marvel, Long> {


}
