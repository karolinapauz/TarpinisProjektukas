package service.xml;

import entity.Aircraft;
import entity.Destination;
import entity.FlightStatus;
import entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "flight")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {

    private Long id;
    private String date;
    private FlightStatus status;
    private Destination destination;
    private Aircraft aircraft;
    private List<Passenger> passengers;



}
