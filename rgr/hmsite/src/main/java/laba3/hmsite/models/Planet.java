package laba3.hmsite.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // example name : Earth
    
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Name can only contain letters, numbers, and spaces")
    private String name;
    // example description : Habitable, for now.
    private String description;
    // Allowed to be blank.

    // assume 'Sun' is parent entity
    // TBD

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
