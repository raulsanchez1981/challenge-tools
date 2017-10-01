package challenge.enums;

/**
 * Created by rsanchpa on 27/09/2017.
 */
public enum Rol {

    ADMIN("Admin"),
    USER("User");

    private final String value;

    public String getValue() {
        return value;
    }

    Rol(String value) {
        this.value = value;
    }


}
