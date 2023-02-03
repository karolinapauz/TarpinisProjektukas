package service;

import entity.Flight;

import java.util.List;

public class PrinterService {

    public String printList(List<Flight> flights){
        String flightDetails = "";
        for (Flight flight : flights) {
            flightDetails += flight.getDestination().getAirport() + ", " + flight.getAircraft().getModel() + ", " + flight.getDate() + ", " + flight.getStatus() + "\n";
        }
        return flightDetails;
    }


}
