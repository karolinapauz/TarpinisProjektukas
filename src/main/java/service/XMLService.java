package service;

import entity.Flight;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class XMLService {

    public void writeToXML(List<Flight> flights) throws JAXBException {
        File file = new File("src/main/resources/flights.xml");
        for (Flight flight : flights) {
            JAXBContext jaxbContext = JAXBContext.newInstance(Flight.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(flight, file);
        }
    }
}
