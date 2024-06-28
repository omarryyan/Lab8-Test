package org.example.entities;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String licensePlate;
    private String image;
    private double price;
    @Column(name = "manufacturing_year")
    private int year;

    @ManyToMany
    @JoinTable(
            name = "car_garage",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "garage_id"))
    Set<Garage> garages = new HashSet<Garage>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Owner_id")
    private Person person;


    public Car() { }
    public Car(String licensePlate, double price, int year, String image ) {
        super();
        this.licensePlate = licensePlate;
        this.price = price;
        this.year = year;
        this.image = image;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getOwner_id() {
        return this.person.getId();
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getId() {
        return id;
    }

    public void setOwner(Person person) {
        this.person = person;
    }

    public Person getOwner() {
        return person;
    }

    public Set<Garage> getGarages() {
        return garages;
    }

    public void setGarages(Set<Garage> garages) {
        this.garages = garages;
    }

    public void addGarage(Garage garage) {
        this.garages.add(garage);
        garage.cars.add(this);
    }

    public void removeGarage(Garage garage) {
        this.garages.remove(garage);
        garage.cars.remove(this);
    }

    @Override
    public String toString() {
        return "Car [License Plate=" + licensePlate + ", Price=" + price + ", Year=" + year + ", Image=" + image + "]\n"  + "Owner:" + person  ;
    }
}
