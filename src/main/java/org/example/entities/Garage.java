package org.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "garages")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String garage_phone;
    private String owner_phone;
    public Garage() {}
    public Garage(String address, String garage_phone, String owner_phone) {
        this.address = address;
        this.garage_phone = garage_phone;
        this.owner_phone = owner_phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setGarage_phone(String garage_phone) {
        this.garage_phone = garage_phone;
    }
    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }
    public String getAddress() {
        return address;
    }
    public String getGarage_phone() {
        return garage_phone;
    }
    public String getOwner_phone() {
        return owner_phone;
    }

    public int getId() {
        return id;
    }
}
