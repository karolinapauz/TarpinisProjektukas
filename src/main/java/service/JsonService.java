package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Flight;
import entity.Pilot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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

//        List<Object> flightList = flights.stream()
//                .collect(Collectors.toList());

        return flights;
    }
}
