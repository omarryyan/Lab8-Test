package org.example.entities;



import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "people")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "personal_name")
    private String name;
    private String family;
    @Column(name = "personal_password")
    private String password;
    private String email;
    @ManyToMany
    @JoinTable(
            name = "person_garage",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "garage_id"))
    Set<Garage> garagesOwned;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Car> cars = new HashSet<>();

    public Person(){}
    public Person(String name, String family, String password, String email) {
        this.name = name;
        this.family = family;
        this.password = password;
        this.email = email;
    }
    //not sure if we want to implement adding cars this way
    public void addCar(Car car) {
        cars.add(car);
        car.setOwner(this);
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setFamily(String family){
        this.family = family;
    }
    public String getFamily(){
        return family;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User [Name=" + name + ", Family=" + family + ", Password=" + password + ", Email=" + email + "]";
    }


}
