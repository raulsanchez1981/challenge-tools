package challenge.utils;

import challenge.entities.Character;

/**
 * Created by cbougeno on 27/09/2017.
 */
public final class Utilities {

    public static Character mapCharacter(Character oldCharacter, Character newCharacter) {
        if (null != oldCharacter && null != newCharacter) {
            if (null != newCharacter.getName()){
                oldCharacter.setName(newCharacter.getName());
            }
            if (null != newCharacter.getPowers()){
                oldCharacter.setPowers(newCharacter.getPowers());
            }
            if (null != newCharacter.getAlterEgo()){
                oldCharacter.setAlterEgo(newCharacter.getAlterEgo());
            }
            if (null != newCharacter.getColor()){
                oldCharacter.setColor(newCharacter.getColor());
            }
            if (null != newCharacter.getStrength()){
                oldCharacter.setStrength(newCharacter.getStrength());
            }
            if (null != newCharacter.getDescription()){
                oldCharacter.setDescription(newCharacter.getDescription());
            }
        }
        return oldCharacter;
    }
}
