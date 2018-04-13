package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "ad") // "Go look over on the bean property named 'ad' on the thing I have a collection of to find the configuration."mappedBy = "ad",
    @JsonIgnore
    private List<Bid> bid;//ponuda

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;//korisnik koji je objavio oglas

    private Long priceAd;//cena koju je postavio u oglasu

    private Date dateEndOfBids;//datum isteka za prikupljanje ponuda

    private String nameAd;

    private String description;

    private String imageAd;

    @Enumerated(EnumType.STRING)
    private AdBidStatus adBidStatus;

    public Ad() {

    }

    public String getNameAd() {
        return nameAd;
    }

    public void setNameAd(String nameAd) {
        this.nameAd = nameAd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Long getPriceAd() {
        return priceAd;
    }

    public Date getDateEndOfBids() {
        return dateEndOfBids;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPriceAd(Long priceAd) {
        this.priceAd = priceAd;
    }

    public void setDateEndOfBids(Date dateEndOfBids) {
        this.dateEndOfBids = dateEndOfBids;
    }

    public void setBid(List<Bid> bid) {
        this.bid = bid;
    }

    public String getImageAd() {
        return imageAd;
    }

    public void setImageAd(String imageAd) {
        this.imageAd = imageAd;
    }

    public List<Bid> getBid() {

        return bid;
    }

    public AdBidStatus getAdBidStatus() {
        return adBidStatus;
    }

    public void setAdBidStatus(AdBidStatus adBidStatus) {
        this.adBidStatus = adBidStatus;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", bid=" + bid +
                ", user=" + user +
                ", priceAd=" + priceAd +
                ", dateEndOfBids=" + dateEndOfBids +
                ", nameAd='" + nameAd + '\'' +
                ", description='" + description + '\'' +
                ", imageAd='" + imageAd + '\'' +
                ", adBidStatus=" + adBidStatus +
                '}';
    }
}
