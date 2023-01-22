package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String model;
    private Integer capacity;
    private Integer maxDistance;

    @ToString.Exclude
    @OneToMany (mappedBy = "aircraft", cascade = CascadeType.ALL)
    @XmlElement
    private List<Pilot> pilots;

    @ToString.Exclude
    @OneToOne
    @JoinColumn (name = "flight_id")
    private Flight flight;


}
