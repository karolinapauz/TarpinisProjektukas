package service.xml;

import entity.Flight;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLService {

    //    public void writeToXML(List<Flight> flights) throws JAXBException {
//        File file = new File("src/main/resources/flights.xml");
//        for (Flight flight : flights) {
//            JAXBContext jaxbContext = JAXBContext.newInstance(Flight.class);
//            Marshaller marshaller = jaxbContext.createMarshaller();
//            marshaller.marshal(flight, file);
//        }
//    }
    public void writeToXML(List<Flight> flights) throws JAXBException {
//        File file = new File("src/main/resources/flights.xml");
//        JAXBContext jaxbContext = JAXBContext.newInstance(Flight.class);
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        if (file.exists()) {
//            file.delete();
//        }
//        try (FileWriter fileWriter = new FileWriter(file, true)) {
//            for (Flight flight : flights) {
//                marshaller.marshal(flight, fileWriter);
//            }
//        } catch (IOException e) {
//            System.out.println("An error occurred while writing to file: " + e.getMessage());
//        }

        Flights flights1 = new Flights();
        List<FlightDto> flightDtos = new ArrayList<>();
        for (Flight flight : flights) {
            FlightDto flightDto = new FlightDto();
            flightDto.setId(flight.getId());
            flightDto.setDate(flight.getDate());

           // flightDto.setAircraft(flight.getAircraft());
            flightDto.setStatus(flight.getStatus());
            flightDtos.add(flightDto);
        }

        flights1.setFlights(flightDtos);

        JAXBContext jaxbContext = JAXBContext.newInstance(Flights.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //Marshal the employees list in file
        jaxbMarshaller.marshal(flights1, new File("src/main/resources/flights.xml"));
    }
}
