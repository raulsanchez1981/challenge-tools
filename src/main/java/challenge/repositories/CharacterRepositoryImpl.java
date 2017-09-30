package challenge.repositories;

import challenge.entities.Character;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.repository.query.parser.Part;

import java.util.List;

/**
 * Created by rsanchpa on 27/09/2017.
 */
public class CharacterRepositoryImpl implements CustomCharacterRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Character> obtainCharactersByCharacter(String userName, Character character) {
        Query query = new Query();
        if (!StringUtils.isEmpty(character.getName())) {
            query.addCriteria(Criteria.where("name").regex(toLikeRegex(character.getName()), "i"));
        }
        if (!StringUtils.isEmpty(character.getAlterEgo())) {
            query.addCriteria(Criteria.where("alterEgo").regex(toLikeRegex(character.getAlterEgo()), "i"));
        }
        if (!StringUtils.isEmpty(character.getDescription())) {
            query.addCriteria(Criteria.where("description").regex(toLikeRegex(character.getDescription()), "i"));
        }
        if (null!=character.getPowers() && !character.getPowers().isEmpty()) {
            query.addCriteria(Criteria.where("powers").in(character.getPowers()));
        }
        Criteria criteriaOr = new Criteria();
        criteriaOr.orOperator(Criteria.where("userCreation").is(userName), Criteria.where("userCreation").exists(false));
        query.addCriteria(criteriaOr);
        List<Character> list = this.mongoTemplate.find(query, Character.class);
        return list;
    }

    @Override
    public void updateCharacter(Character character) {
        DBObject userDBObject = (DBObject) mongoTemplate.getConverter().convertToMongoType(character);
        //Create setUpdate & query
        Update setUpdate = Update.fromDBObject(new BasicDBObject("$set", userDBObject));
        mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(character.getId())), setUpdate , Character.class);
        //Or use native mongo
        //mongoTemplate.getDb().getCollection("user").update(new BasicDBObject("_id",user.getId()), new BasicDBObject("$set", userDBObject), false, false);
    }

    private String toLikeRegex(String source) {
        return MongoRegexCreator.INSTANCE.toRegularExpression(source, Part.Type.EXISTS);
    }

}
