package challenge.utils;

import challenge.entities.Marvel;

/**
 * Created by cbougeno on 27/09/2017.
 */
public final class Utilities {

    public static Marvel mapMarvel(Marvel oldMarvel, Marvel newMarvel) {
        if (null != oldMarvel && null != newMarvel) {
            if (null != newMarvel.getHero()){
                oldMarvel.setHero(newMarvel.getHero());
            }
            if (null != newMarvel.getPowers()){
                oldMarvel.setPowers(newMarvel.getPowers());
            }
            if (null != newMarvel.getAlterEgo()){
                oldMarvel.setAlterEgo(newMarvel.getAlterEgo());
            }
            if (null != newMarvel.getColor()){
                oldMarvel.setColor(newMarvel.getColor());
            }
            if (null != newMarvel.getStrength()){
                oldMarvel.setStrength(newMarvel.getStrength());
            }
            if (null != newMarvel.getDescription()){
                oldMarvel.setDescription(newMarvel.getDescription());
            }
        }
        return oldMarvel;
    }
}
