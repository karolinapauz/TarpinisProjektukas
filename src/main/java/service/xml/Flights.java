package service.xml;

import entity.Flight;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "flights")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Flights {

    @XmlElement(name = "flight")
    List<FlightDto> flights = null;


}
