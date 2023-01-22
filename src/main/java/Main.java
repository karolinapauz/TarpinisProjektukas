import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.reflect.TypeToken;
import entity.Aircraft;
import entity.Flight;
import entity.Passenger;
import entity.Pilot;
import org.hibernate.Session;
import repository.FlightRepo;
import service.JsonService;
import com.google.gson.Gson;
import util.HibernateUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        Session session = HibernateUtil.getSessionFactory().openSession();

        JsonService jsonService = new JsonService();
        List<Flight> jsonToList = jsonService.getDataFromJsonToList();

        List<Object> flightList = jsonToList.stream()
                .collect(Collectors.toList());


        // List<Flight> flights = jsonService.readJson();


        FlightRepo flightRepo = new FlightRepo();
        Flight flight1 = new Flight();
        flight1.setDate("2009");
        Aircraft aircraft = new Aircraft();
        aircraft.setFlight(flight1);
        Pilot pilot = new Pilot();
        pilot.setAircraft(aircraft);
        aircraft.setPilots(Arrays.asList(pilot));
        flight1.setAircraft(aircraft);
        Passenger passenger = new Passenger();
        Passenger passenger1 = new Passenger();
        flight1.setPassengers(Arrays.asList(passenger, passenger1));



        flightRepo.save(flightList);


    }
}
