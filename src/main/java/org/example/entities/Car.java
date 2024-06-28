package org.example.entities;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int owner_id;
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
    Set<Garage> garagesAccepted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Person_id")
    private Person person;


    public Car() { }
    public Car(String licensePlate, double price, int year,int owner_id ) {
        super();
        this.licensePlate = licensePlate;
        this.price = price;
        this.year = year;
        this.owner_id = owner_id;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public int getOwner_id() {
        return owner_id;
    }
    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
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
        this.owner_id= person.getId();
    }
}
