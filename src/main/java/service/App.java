package service;

import com.itextpdf.text.DocumentException;
import entity.Flight;
import repository.FlightRepo;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class App extends JFrame {
    private JTextField enteredName;
    private JButton search;
    private JPanel myPanel;
    private JButton allFlightsButton;
    private JButton printButton;
    private JTextField nameTicket;
    private JTextField flightNumber;

    FlightRepo flightRepo = new FlightRepo();
    List<Flight> flights = flightRepo.findAll();
    SearchService searchService = new SearchService();
    PrinterService printer = new PrinterService();
    PdfService pdfService = new PdfService();

    public App() throws HeadlessException {
        setTitle("Airport Passengers Management System");
        setVisible(true);
        setSize(750,500);
        setContentPane(myPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setSearchButton();
        setAllFlightsButton();
        setPrintButton();
    }

    public void setSearchButton() {
        myPanel.setBackground(new Color(87, 160, 211));
        search.setFont(new Font("Comic Sans", Font.BOLD, 14));
        search.addActionListener(actionEvent -> {
            String enteredNameText = enteredName.getText();
            Optional<Flight> flightByPassenger = searchService.getFlightByPassenger(flights, enteredNameText);
            JOptionPane.showMessageDialog(search, "Destination: " + flightByPassenger.get().getDestination() + " date: " + flightByPassenger.get().getDate() + " , status: "
                    + flightByPassenger.get().getStatus());
        });
    }

    public void setAllFlightsButton() {
        allFlightsButton.addActionListener(actionEvent -> {
            String flightsList = printer.printList(flights);
            JOptionPane.showMessageDialog(allFlightsButton, "Flights: " + flightsList);
        });
    }

    public void setPrintButton() {
        printButton.addActionListener(actionEvent -> {
            String text = flightNumber.getText();
            int flightNr = Integer.parseInt(text) - 1;

            String passNr = nameTicket.getText();
            int passengerId = Integer.parseInt(passNr) - 1;
            try {
                pdfService.convertToPdf(flights.get(flightNr), flights.get(flightNr).getPassengers().get(passengerId));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
            File pdfFile = new File("sample1.pdf");
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(pdfFile);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    JOptionPane.showMessageDialog(printButton, "Unable to open PDF. Desktop is not supported");
                }
        });
    }


}

