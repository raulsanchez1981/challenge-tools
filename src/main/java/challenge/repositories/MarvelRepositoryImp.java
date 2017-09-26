package challenge.repositories;

import challenge.entities.Marvel;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * Created by cbougeno on 26/09/2017.
 */
public class MarvelRepositoryImp implements MarvelRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public int update(String hero, String power) {
        Query query = new Query(Criteria.where("hero").is(hero));
        Update update = new Update();
        update.set("power", power);

        WriteResult result = mongoTemplate.updateFirst(query, update, Marvel.class);

        if (result != null)
            return result.getN();
        else
            return 0;
    }
}
