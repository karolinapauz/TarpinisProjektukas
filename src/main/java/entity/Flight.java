package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
