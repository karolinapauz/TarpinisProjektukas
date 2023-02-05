import entity.*;
import repository.FlightRepo;
import service.*;
import service.xml.XMLService;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, JAXBException {

        JsonService jsonService = new JsonService();
        List<Flight> dataFromJson = jsonService.getDataFromJsonToList();

        FlightRepo flightRepo = new FlightRepo();
        flightRepo.saveAll(dataFromJson);
        //flightRepo.delete(flights.get(0));

        List<Flight> flightList = flightRepo.findAll();

        SearchService searchService = new SearchService();

        Pilot mostExperienced = searchService.findMostExperienced(flightList);
        System.out.println(mostExperienced.getFirstName());
        System.out.println(searchService.calculateTotalFlights(flightList));

        List<Passenger> passengerByName = searchService.getPassengerByName(flightList, "Tim");
        passengerByName.forEach(System.out::println);

        List<Passenger> passengerByNationality = searchService.getPassengerByNationality(flightList, "lithuanian");
        passengerByNationality.forEach(System.out::println);

        List<Flight> flightsByStatus = searchService.getFlightsByStatus(flightList, FlightStatus.ON_TIME);
        flightsByStatus.forEach(System.out::println);

        System.out.println(searchService.getFlightByPassenger(flightList, "Tim"));


        Pilot pilot = flightList.get(0).getAircraft().getPilots().get(0);
        pilot.setFirstName("Jonas");
        flightRepo.update(pilot);

        new XMLService().writeToXML(flightList);

        App app = new App();

    }
}
