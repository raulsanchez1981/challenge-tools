package challenge.services;

import challenge.entities.Marvel;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
public interface MarvelServices {

    List<Marvel> obtainAllMarvels();

    List<Marvel> obtainMarvelFiltering(String hero, String power, String alterEgo, String color, Integer strength);

    Marvel obtainMarvelById(String id);

    List<String> obtainPowers();

    List<String> obtainColors();

    Marvel saveMarvel(Marvel marvel);

    Marvel updateMarvel(String id, Marvel marvel);

    Marvel removeMarvel(String id, Marvel marvel);
}
