package service.xml;

import entity.Flight;
import entity.Pilot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class AircraftDto {

    private Long id;
    private String model;
    private Integer capacity;
    private Integer airTime;
    private List<Pilot> pilots;
    @XmlTransient
    private Flight flight;
}
