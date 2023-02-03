package entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeName("pilot")
public class Pilot extends Person implements Persistable{

    private Integer yearsOfExperience;
    private Integer totalFlights;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    @XmlTransient
    private Aircraft aircraft;

}
