package org.example.entities;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="image_url")
    private String url;
    @OneToOne(mappedBy = "image")
    private Person person;

    public Image() {}
    public Image(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
