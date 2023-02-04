package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Destination implements Persistable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String airport;
    private Integer travelTime;

    @ToString.Exclude
    @OneToOne
   // @XmlTransient
    @JoinColumn (name = "flight_id")
    private Flight flight;

}
