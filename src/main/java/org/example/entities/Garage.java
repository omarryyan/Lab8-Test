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

    @ManyToMany(mappedBy = "garages")
    Set<Person> owners = new HashSet<>();

    @ManyToMany(mappedBy = "garages")
    Set<Car> cars = new HashSet<>();

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
        this.cars.add(car);
        car.addGarage(this);
    }

    public void removeCar(Car car) {
        this.cars.remove(car);
        car.removeGarage(this);
    }

    public void addPerson(Person person) {
        owners.add(person);
        person.addGarage(this);
    }
    public void removePerson(Person person) {
        owners.remove(person);
        person.removeGarage(this);
    }

    @Override
    public String toString() {
        return "Garage [Address=" + address + ", Phone=" + garage_phone + "]";
    }
}
