package service;

import entity.Flight;
import entity.FlightStatus;
import entity.Passenger;
import entity.Pilot;
import repository.FlightRepo;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchService {

    static FlightRepo flightRepo = new FlightRepo();
    public static List<Flight> flightList = flightRepo.findAll();

    public SearchService() {
        Pilot mostExperienced = findMostExperienced(flightList);
        System.out.println(mostExperienced.getFirstName());
        System.out.println(calculateTotalFlights(flightList));

        List<Passenger> passengerByName = getPassengerByName(flightList, "Tim");
        passengerByName.forEach(System.out::println);

        List<Passenger> passengerByNationality = getPassengerByNationality(flightList, "lithuanian");
        passengerByNationality.forEach(System.out::println);

        List<Flight> flightsByStatus = getFlightsByStatus(flightList, FlightStatus.ON_TIME);
        flightsByStatus.forEach(System.out::println);

        System.out.println(getFlightByPassenger(flightList, "Tim"));
    }

    public Pilot findMostExperienced(List<Flight> flights) {
        return flights.stream()
                .flatMap(flight -> flight.getAircraft().getPilots().stream())
                .max(Comparator.comparing(Pilot::getYearsOfExperience))
                .stream().findFirst().orElse(null);
    }

    public Integer calculateTotalFlights(List<Flight> flights) {
        return flights.stream()
                .flatMap(flight -> flight.getAircraft().getPilots().stream())
                .mapToInt(Pilot::getTotalFlights)
                .sum();
    }

    public List<Passenger> getPassengerByName(List<Flight> flights, String name) {
        return flights.stream()
                .flatMap(flight -> flight.getPassengers().stream())
                .filter(passenger -> name.equalsIgnoreCase(passenger.getFirstName()))
                .collect(Collectors.toList());
    }

    public Optional<Flight> getFlightByPassenger(List<Flight> flights, String name) {
        return flights.stream()
                .filter(f -> f.getPassengers().stream()
                        .anyMatch(p -> name.equalsIgnoreCase(p.getFirstName())))
                .findFirst();
    }

    public List<Passenger> getPassengerByNationality(List<Flight> flights, String nationality) {
        return flights.stream()
                .flatMap(flight -> flight.getPassengers().stream())
                .filter(passenger -> nationality.equalsIgnoreCase(passenger.getNationality()))
                .collect(Collectors.toList());
    }

    public List<Flight> getFlightsByStatus(List<Flight> flights, FlightStatus status) {
        return flights.stream()
                .filter(flight -> status.equals(flight.getStatus()))
                .collect(Collectors.toList());
    }
}

