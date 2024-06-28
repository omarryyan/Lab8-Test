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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_image_id")
    private Image image;
    private double price;
    @Column(name = "manufacturing_year")
    private int year;

    @ManyToMany
    @JoinTable(
            name = "car_garage",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "garage_id"))
    Set<Garage> garagesAccepted = new HashSet<Garage>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Owner_id")
    private Person person;


    public Car() { }
    public Car(String licensePlate, double price, int year, Image image ) {
        super();
        this.licensePlate = licensePlate;
        this.price = price;
        this.year = year;
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
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

    @Override
    public String toString() {
        return "Car [License Plate=" + licensePlate + ", Price=" + price + ", Year=" + year + ", Image=" + image + "]\n"  + "Owner:" + person  ;
    }
}
