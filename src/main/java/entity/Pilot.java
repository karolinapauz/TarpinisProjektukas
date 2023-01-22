package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeName("pilot")
public class Pilot extends Person {

    private Integer yearsOfExperience;
    private Integer totalFlights;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

}
