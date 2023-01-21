package entity;

import javax.persistence.Entity;

@Entity
public class Pilot extends Person {

    private int yearsOfExperience;
    private int totalFlights;
}
