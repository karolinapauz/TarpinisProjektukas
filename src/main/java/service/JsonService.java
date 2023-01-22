package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import entity.Aircraft;
import entity.Flight;
import entity.Passenger;
import entity.Pilot;
import repository.FlightRepo;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonService {

    public List<Flight> getDataFromJsonToList() throws IOException {
        String file = "src/main/resources/flights.json";
        String json = new String(Files.readAllBytes(Paths.get(file)));

        ObjectMapper mapper = new ObjectMapper();

        List<Flight> flights = mapper.readValue(json, new TypeReference<>() {
        });

        for (Flight flight : flights) {
            flight.getAircraft().setFlight(flight);
            for (Pilot pilot : flight.getAircraft().getPilots()) {
                pilot.setAircraft(flight.getAircraft());
            }
            flight.getDestination().setFlight(flight);
        }
        return flights;
    }

//    FlightRepo flightRepo = new FlightRepo();
//    Flight flight1 = new Flight();
//        flight1.setDate("2009");
//    Aircraft aircraft = new Aircraft();
//        aircraft.setFlight(flight1);
//    Pilot pilot = new Pilot();
//        pilot.setAircraft(aircraft);
//        aircraft.setPilots(Arrays.asList(pilot));
//        flight1.setAircraft(aircraft);
//    Passenger passenger = new Passenger();
//    Passenger passenger1 = new Passenger();
//        flight1.setPassengers(Arrays.asList(passenger, passenger1));

//    public List<Flight> readJson() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(new File("src/main/resources/flights.json"));
//       // List<JsonNode> nodes = root.findValues("Flight");
////        for (JsonNode node : nodes) {
////            System.out.println(node);
////        }
//        List<Flight> flights = root.findValues("Aircraft").stream()
//                .map(node -> {
//                    try {
//                        return mapper.treeToValue(node, Flight.class);
//                    } catch (JsonProcessingException e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .collect(Collectors.toList());
//
//        return flights;
//    }
//
//    public void readGsonfromJson() throws IOException {
//        Gson gson = new Gson();
//        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/flights.json"));
//        List<Flight> flights = new Gson().fromJson(reader, new TypeToken<List<Flight>>() {}.getType());
//
//        flights.forEach(System.out::println);
//        reader.close();
//    }
//

}
