package challenge.enums;

/**
 * Created by rsanchpa on 27/09/2017.
 */
public enum Power {

    FLIGHT("Flight"),
    SUPER_STRENGTH("Strength"),
    MAGIC("Magic"),
    TELEPATHY("Telepathy"),
    TELEKINESIS("Telekinesis"),
    TELEPORTATION("Teleportation"),
    INTELLECT("Intellect"),
    SHAPE_SHIFTER("Shape-Shifter"),
    SUPER_SPEED("Speed"),
    INVULNERABILITY("Invulnerability"),
    INMORTAL("Inmortal");

    public String getValue() {
        return value;
    }

    private final String value;


    Power(String value) {
        this.value = value;
    }


}
