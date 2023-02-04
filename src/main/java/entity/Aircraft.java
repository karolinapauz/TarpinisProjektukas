package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Aircraft implements Persistable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String model;
    private Integer capacity;
    private Integer airTime;

    @ToString.Exclude
    @OneToMany (mappedBy = "aircraft", cascade = CascadeType.ALL)
    private List<Pilot> pilots;

    @ToString.Exclude
    @OneToOne
    @JoinColumn (name = "flight_id")
    private Flight flight;


}
