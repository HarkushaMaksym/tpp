package laba3.hmsite.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "satellite")
public class Satellite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // example name : Moon
    @NotBlank(message = "Name cannot be blank")
    @Pattern(regexp = "^[\\p{L}0-9 ]+$", message = "Name can only contain letters, numbers, and spaces")
    private String name;

    // example description : Satellite of Earth.
    private String description;


    @NotNull(message = "Planet cannot be null")
    @ManyToOne
    @JoinColumn(name="planet_id", nullable=false)
    private Planet planet;

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
    public Planet getPlanet() {
        return planet;
    }
    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

}
