package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ad")
    private Ad ad;

    private Long idGuestBid;
    private Long priceBid;

    @Enumerated(EnumType.STRING)
    private AdBidStatus adBidStatus;

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

    public AdBidStatus getAdBidStatus() {
        return adBidStatus;
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

    public void setAdBidStatus(AdBidStatus adBidStatus) {
        this.adBidStatus = adBidStatus;
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
