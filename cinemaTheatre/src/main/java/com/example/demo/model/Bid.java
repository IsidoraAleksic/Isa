package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ad")
    private Ad ad;

    private Long idGuestBid;
    private Long priceBid;

    public Bid() {

    }

    public Long getId() {
        return id;
    }

    public Ad getAd() {
        return ad;
    }

    public Long getIdGuestBid() {
        return idGuestBid;
    }

    public Long getPriceBid() {
        return priceBid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public void setIdGuestBid(Long idGuestBid) {
        this.idGuestBid = idGuestBid;
    }

    public void setPriceBid(Long priceBid) {
        this.priceBid = priceBid;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id=" + id +
                ", ad=" + ad +
                ", idGuestBid=" + idGuestBid +
                ", priceBid=" + priceBid +
                '}';
    }
}
