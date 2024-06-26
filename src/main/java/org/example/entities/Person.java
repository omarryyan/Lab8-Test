package org.example.entities;

import javax.persistence.*;

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

    public Person(){}
    public Person(String name, String family, String password, String email) {
        this.name = name;
        this.family = family;
        this.password = password;
        this.email = email;
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
}
