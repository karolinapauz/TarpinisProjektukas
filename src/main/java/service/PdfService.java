package service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import entity.Flight;
import entity.Passenger;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfService {

    public void convertToPdf(Flight flight, Passenger passenger) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/Flight_ticket.pdf"));
        document.open();


        Paragraph para = new Paragraph("\n");
        document.add(para);
        document.add(para);
        document.add(para);


        para = new Paragraph("e-Ticket receipt");
        Font f = new Font(Font.FontFamily.TIMES_ROMAN, 10.0f, Font.ITALIC, BaseColor.BLACK.brighter());
        para.setAlignment(Element.ALIGN_CENTER);
        para.setFont(f);
        document.add(para);


        para = new Paragraph("Traveler: " + passenger.getFirstName() + " " + passenger.getLastName());
        document.add(para);

        para = new Paragraph("Flight date : " + flight.getDate());
        document.add(para);

        para = new Paragraph("Destination : " + flight.getDestination().getAirport());
        document.add(para);


        Image barcode = getBarcode(flight, passenger, document);
        barcode.setAlignment(Element.ALIGN_RIGHT);
        document.add(barcode);


        para = new Paragraph("Check-in: 24 hours before departure ( either online or at airport)\n" +
                "Baggage Allowance: 1 check-in bag, 1 carry-on bag (weight and dimension limit may vary)\n" +
                "Seat Assignment: 12A (upon request)\n" +
                "Special Request: Vegetarian Meal\n" +
                "Barcode: 12345678 (electronic boarding pass)\n" +
                "Connections: None\n" +
                "Baggage Fees: $25 for additional check-in bag, $50 for overweight bag, $75 for oversized bag\n" +
                "Prohibited items:\n" +
                "\n" +
                "Hazardous materials such as explosives, flammable liquids and gases, toxic or poisonous materials\n" +
                "Illegal drugs, firearms, and other weapons\n" +
                "Foods that may attract pests, like fruits and vegetables\n" +
                "Disclaimer: Flight schedule and routes are subject to change. Check-in, baggage and prohibited items policy may vary and subject to change, please check with the airline for the most updated information.");

        document.add(para);
        para = new Paragraph("\n");
        document.add(para);
        document.add(para);
        document.add(para);
        document.add(para);
        document.add(para);
        document.add(para);
        document.add(para);
        document.add(para);

        Image img = Image.getInstance("src/main/resources/FotoJet.jpg");
        img.scaleAbsolute(500f, 250f);
        img.setSpacingBefore(100f);
        img.setAlignment(Element.ALIGN_CENTER);
        document.add(img);

        document.close();
        System.out.println("Successfull.");


    }

    public Image getBarcode(Flight flight, Passenger passenger, Document document) throws FileNotFoundException, DocumentException {
        Barcode128 barcode128 = new Barcode128();
        barcode128.setCode(flight.getDate() + passenger.getFirstName() + passenger.getLastName() + passenger.getId());
        barcode128.setCodeType(Barcode.CODE128);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("sample1.pdf"));
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        Image code128Image = barcode128.createImageWithBarcode(cb, BaseColor.BLACK, BaseColor.BLACK);
        return code128Image;

    }
}