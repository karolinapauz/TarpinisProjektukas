package service;

import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {

    SearchService searchService = new SearchService();

    public static List<Flight> getTestData() {
        List<Flight> flights = new ArrayList<>();
        Flight flight = new Flight();
        Flight flight2 = new Flight();
        flight.setStatus(FlightStatus.DELAYED);
        flight2.setStatus(FlightStatus.ARRIVED);
        Pilot pilot = new Pilot();
        Pilot pilot2 = new Pilot();
        pilot.setYearsOfExperience(12);
        pilot2.setTotalFlights(65);
        pilot2.setYearsOfExperience(23);
        pilot.setTotalFlights(12);
        Aircraft aircraft = new Aircraft();
        aircraft.setPilots(Arrays.asList(pilot, pilot2));
        aircraft.setFlight(flight);
        pilot2.setAircraft(aircraft);
        Passenger passenger = new Passenger();
        Passenger passenger2 = new Passenger();
        passenger2.setFirstName("Migle");
        passenger2.setNationality("lithuanian");
        passenger.setNationality("latvian");
        passenger.setFirstName("Tomas");
        pilot.setAircraft(aircraft);
        flight.setAircraft(aircraft);
        flight.setPassengers(Arrays.asList(passenger));
        flights.add(flight);
        return flights;
    }

    public static List<Flight> flights = getTestData();

    @Test
    void findMostExperiencedPilot() {
        Pilot mostExperienced = searchService.findMostExperienced(flights);
        assertEquals(flights.get(0).getAircraft().getPilots().get(1), mostExperienced);
    }

    @Test
    void calculateTotalFlights() {
        Integer totalFlights = searchService.calculateTotalFlights(flights);
        assertEquals(77, totalFlights);

    }

    @Test
    void getPassengerByName() {
        List<Passenger> passengerByName = searchService.getPassengerByName(flights, "Tomas");
        assertEquals(1, passengerByName.size());
        assertEquals("Tomas", passengerByName.get(0).getFirstName());
    }

    @Test
    void getFlightsByStatus() {
        List<Flight> flightsByStatus = searchService.getFlightsByStatus(flights, FlightStatus.DELAYED);
        assertEquals(1, flightsByStatus.size());
        assertEquals(FlightStatus.DELAYED, flightsByStatus.get(0).getStatus());
    }

    @Test
    void getPassengerByNationality() {
        List<Passenger> passengerByNationality = searchService.getPassengerByNationality(flights, "latvian");
        assertEquals(1, passengerByNationality.size());
        assertEquals("latvian", passengerByNationality.get(0).getNationality());
    }

    @Test
    void getFlightByPassenger() {
        Optional<Flight> flightByPassenger = searchService.getFlightByPassenger(flights, "Tomas");
        assertTrue(flightByPassenger.isPresent());
        assertEquals("Tomas", flightByPassenger.get().getPassengers().get(0).getFirstName());


    }
}