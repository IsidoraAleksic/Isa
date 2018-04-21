package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="merchandise")
public class Merchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;//korisnik koji je objavio oglas

    @OneToMany(mappedBy = "merch")
    @JsonIgnore
    private List<ReservationMerchandise> reservationMerchandise;

    private String nameMerchandise;
    private String description;
    private Long priceMerchandise;//ovo treba za blagajnu. kao neka realna cena
    private String imageMerchandise;


    public Merchandise() {

    }
    public Long getId() {
        return id;
    }

    public String getNameMerchandise() {
        return nameMerchandise;
    }

    public String getDescription() {
        return description;
    }

    public Long getPriceMerchandise() {
        return priceMerchandise;
    }

    public String getImageMerchandise() {
        return imageMerchandise;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNameMerchandise(String nameMerchandise) {
        this.nameMerchandise = nameMerchandise;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceMerchandise(Long priceMerchandise) {
        this.priceMerchandise = priceMerchandise;
    }

    public void setImageMerchandise(String imageMerchandise) {
        this.imageMerchandise = imageMerchandise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ReservationMerchandise> getReservationMerchandise() {
        return reservationMerchandise;
    }

    public void setReservationMerchandise(List<ReservationMerchandise> reservationMerchandise) {
        this.reservationMerchandise = reservationMerchandise;
    }


    @Override
    public String toString() {
        return "Merchandise{" +
                "id=" + id +
                ", nameMerchandise='" + nameMerchandise + '\'' +
                ", description='" + description + '\'' +
                ", priceMerchandise=" + priceMerchandise +
                ", imageMerchandise=" + imageMerchandise +
                '}';
    }
}
