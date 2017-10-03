package challenge.search;


import challenge.serialization.LocalDateDeserializer;
import challenge.serialization.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */

public class CharacterSearch {

    @ApiModelProperty(notes = "The Name of the Character", required = true, value = "Super-Heroe", example = "Super-Heroe")
    private String name;

    @ApiModelProperty(notes = "The Alter Ego of the Character", required = true, value = "Pepito", example = "Pepito")
    private String alterEgo;

    @ApiModelProperty(notes = "The Powers of the Character", required = true)
    private List<String> powers;

    @ApiModelProperty(notes = "The Description of the Character", required = true, value = "A normal guy with powers", example = "A normal guy with powers")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlterEgo() {
        return alterEgo;
    }

    public void setAlterEgo(String alterEgo) {
        this.alterEgo = alterEgo;
    }

    public List<String> getPowers() {
        return powers;
    }

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
