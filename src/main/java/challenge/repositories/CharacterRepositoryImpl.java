package challenge.repositories;

import challenge.entities.Character;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.query.parser.Part;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsanchpa on 27/09/2017.
 */
public class CharacterRepositoryImpl implements CustomCharacterRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Character> obtainCharactersByCharacter(Character character) {
        Query query = new Query();
        if (!StringUtils.isEmpty(character.getName())) {
            query.addCriteria(Criteria.where("name").regex(toLikeRegex(character.getName())));
        }
        if (!StringUtils.isEmpty(character.getAlterEgo())) {
            query.addCriteria(Criteria.where("alterEgo").regex(toLikeRegex(character.getAlterEgo())));
        }
        if (!StringUtils.isEmpty(character.getDescription())) {
            query.addCriteria(Criteria.where("description").regex(toLikeRegex(character.getDescription())));
        }
        if (null!=character.getPowers() && !character.getPowers().isEmpty()) {
            query.addCriteria(Criteria.where("powers").in(character.getPowers()));
        }
        List<Character> list = this.mongoTemplate.find(query, Character.class);

        return list;
    }

    private String toLikeRegex(String source) {
        return MongoRegexCreator.INSTANCE.toRegularExpression(source, Part.Type.EXISTS);
    }

}
