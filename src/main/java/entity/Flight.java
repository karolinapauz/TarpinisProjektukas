package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Flight {

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
