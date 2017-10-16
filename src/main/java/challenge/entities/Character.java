package challenge.entities;


import challenge.serialization.LocalDateDeserializer;
import challenge.serialization.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by cbougeno on 26/09/2017.
 */
@CompoundIndexes({
    @CompoundIndex(name = "index_name_userCreation", def = "{'name' : 1, 'userCreation' : 1}", unique = true)
})
@Document(collection = "character")
public class Character {

    @Id
    @ApiModelProperty(notes = "The id of the Character", required = true, hidden = true)
    private String id;

    @NotNull(message = "The 'Name' must be filled")
    @ApiModelProperty(notes = "The Name of the Character", required = true, value = "Super-Heroe", example = "Super-Heroe")
    private String name;

    @NotNull(message = "The 'Alter Ego' must be filled")
    @ApiModelProperty(notes = "The Alter Ego of the Character", required = true, value = "Pepito", example = "Pepito")
    private String alterEgo;

    @ApiModelProperty(notes = "The Powers of the Character", required = true)
    private List<String> powers;

    @NotNull(message = "The 'Strength' must be filled")
    @Max(value = 9, message = "The 'Strength' must be 0-9")
    @ApiModelProperty(notes = "The Strength of the Character", required = true, dataType = "integer", value = "9", example = "9")
    private Integer strength;

    @NotNull(message = "The 'Description' must be filled")
    @ApiModelProperty(notes = "The Description of the Character", required = true, value = "A normal guy with powers", example = "A normal guy with powers")
    private String description;

    @NotNull(message = "The 'Image' must be filled")
    @ApiModelProperty(notes = "The Image's URL of the Character", required = true, value = "http://wheretheimage.es", example = "http://wheretheimage.es")
    private String image;

    @ApiModelProperty(notes = "The Birth Date of the Character", required = true, value = "01/06/1991", example = "01/06/1991")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthDate;

    @JsonIgnore
    private String userCreation;

    @ApiModelProperty(hidden = true, dataType = "boolean")
    private Boolean modifiable;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPowers() {
        return powers;
    }

    public void setPowers(List<String> powers) {
        this.powers = powers;
    }

    public String getAlterEgo() {
        return alterEgo;
    }

    public void setAlterEgo(String alterEgo) {
        this.alterEgo = alterEgo;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserCreation() {
        return userCreation;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public Boolean getModifiable() {
        return (userCreation != null) ? true : false;
    }

}
