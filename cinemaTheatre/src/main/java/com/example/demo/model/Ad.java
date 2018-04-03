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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchandise_id")
    @JsonIgnore
    private Merchandise merchandise;

    @OneToMany(mappedBy = "ad") // "Go look over on the bean property named 'ad' on the thing I have a collection of to find the configuration."mappedBy = "ad",
    @JsonIgnore
    private List<Bid> bid;//oglas

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;//korisnik koji je objavio oglas

    private Long priceAd;//cena koju je postavio u oglasu

    private Date dateEndOfBids;//datum isteka za prikupljanje ponuda

    public Ad(Long id, Merchandise merchandise, User user, Long priceAd, Date dateEndOfBids) {
        this.id = id;
        this.merchandise = merchandise;
        this.user = user;
        this.priceAd = priceAd;
        this.dateEndOfBids = dateEndOfBids;
    }

    public Ad() {

    }
    public Long getId() {
        return id;
    }

    public Merchandise getMerchandise() {
        return merchandise;
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

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
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

    public List<Bid> getBid() {

        return bid;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", merchandise=" + merchandise +
                ", user=" + user +
                ", priceAd=" + priceAd +
                ", dateEndOfBids=" + dateEndOfBids +
                '}';
    }
}
