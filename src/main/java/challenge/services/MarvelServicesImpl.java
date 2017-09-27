package challenge.services;

import challenge.entities.Marvel;
import challenge.repositories.MarvelRepository;
import challenge.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
@Service
public class MarvelServicesImpl implements MarvelServices {

    @Autowired
    private MarvelRepository marvelRepository;

    @Override
    public List<Marvel> obtainAllMarvels() {
        return this.marvelRepository.findAll();
    }

    @Override
    public List<Marvel> obtainMarvelFiltering(String hero, String power, String alterEgo, String color, Integer strength) {
        return null;
    }

    @Override
    public Marvel obtainMarvelById(String id) {
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
        return this.marvelRepository.save(marvel);
    }

    @Override
    public Marvel updateMarvel(String id, Marvel marvelNew) {
        Marvel marvelOld = obtainMarvelById(id);
        Utilities.mapMarvel(marvelOld, marvelNew);
        return null;
    }

    @Override
    public Marvel removeMarvel(String id, Marvel marvel) {
        return null;
    }
}
