package org.example.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "garages")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String garage_phone;

    @ManyToMany(mappedBy = "garagesOwned")
    Set<Person> owners = new HashSet<>();

    @ManyToMany(mappedBy = "garagesAccepted")
    Set<Car> AcceptedCars = new HashSet<>();

    public Garage() {}
    public Garage(String address, String garage_phone) {
        this.address = address;
        this.garage_phone = garage_phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setGarage_phone(String garage_phone) {
        this.garage_phone = garage_phone;
    }

    public String getAddress() {
        return address;
    }

    public String getGarage_phone() {
        return garage_phone;
    }

    public String getOwner_phone() {
        return "TODO: read from the database";
    }

    public int getId() {
        return id;
    }

    public void addOwned(Person person) {
        owners.add(person);
    }

    public void removeOwned(Person person) {
        owners.remove(person);
    }

    public void addCar(Car car) {
        AcceptedCars.add(car);
    }

    public void removeCar(Car car) {
        AcceptedCars.remove(car);
    }

    @Override
    public String toString() {
        return "Garage [Address=" + address + ", Phone=" + garage_phone + "]";
    }
}
