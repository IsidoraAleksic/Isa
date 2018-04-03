package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="merchandise")
public class Merchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ad")
    @JsonIgnore
    private Ad ad;

    private String nameMerchandise;
    private String description;
    private Long priceMerchandise;//ovo treba za blagajnu. kao neka realna cena
    private String imageMerchandise;

    public Merchandise(Long id, String nameMerchandise, String description, Long priceMerchandise, String imageMerchandise) {
        this.id = id;
        this.nameMerchandise = nameMerchandise;
        this.description = description;
        this.priceMerchandise = priceMerchandise;
        this.imageMerchandise = imageMerchandise;
    }
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
