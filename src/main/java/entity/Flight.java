package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long id;
    private LocalDateTime date;
    private FlightStatus status;

    @OneToOne  (mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Destination destination;

    @OneToOne  (mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private Aircraft aircraft;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable (name = "flight_passenger",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<Passenger> passengers;
}
