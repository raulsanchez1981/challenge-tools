package challenge.services;

import challenge.entities.Marvel;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
public class MarvelServicesImpl implements MarvelServices {
    @Override
    public List<Marvel> obtainAllMarvels() {
        return null;
    }

    @Override
    public List<Marvel> obtainMarvelFiltering(String hero, String power, String alterEgo, String color, Integer strength) {
        return null;
    }

    @Override
    public List<Marvel> obtainMarvelById(String id) {
        return null;
    }

    @Override
    public List<String> obtainPowers() {
        return null;
    }

    @Override
    public List<String> obtainColors() {
        return null;
    }

    @Override
    public Marvel saveMarvel(Marvel marvel) {
        return null;
    }

    @Override
    public Marvel updateMarvel(String id, Marvel marvel) {
        return null;
    }

    @Override
    public Marvel removeMarvel(String id, Marvel marvel) {
        return null;
    }
}
