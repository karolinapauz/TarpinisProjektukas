package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String airport;
    private Integer travelTime;

    @ToString.Exclude
    @OneToOne
    @JoinColumn (name = "flight_id")
    private Flight flight;

}
