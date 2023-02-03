import entity.*;
import repository.FlightRepo;
import service.*;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException {

        JsonService jsonService = new JsonService();
        List<Flight> flights = jsonService.getDataFromJsonToList();

        FlightRepo flightRepo = new FlightRepo();
        flightRepo.saveAll(flights);
        //flightRepo.delete(flights.get(0));
        List<Flight> flightList = flightRepo.findAll();

        XMLService xmlService = new XMLService();
        xmlService.writeToXML(flightList);

        App app = new App();

    }
}
