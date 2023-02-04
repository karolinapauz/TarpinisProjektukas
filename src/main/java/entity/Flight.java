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
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlRootElement(name = "flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight implements Persistable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String date;
    private FlightStatus status;

    @ToString.Exclude
    @OneToOne (mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Destination destination;

    @ToString.Exclude
    @OneToOne  (mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Aircraft aircraft;

    @ToString.Exclude
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<Passenger> passengers;
}
