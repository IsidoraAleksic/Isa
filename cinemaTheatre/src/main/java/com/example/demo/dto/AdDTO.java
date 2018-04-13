package com.example.demo.dto;

import com.example.demo.model.Ad;

import java.util.ArrayList;
import java.util.Date;

public class AdDTO {

    private Long merchandiseId;

    private Long userId;//korisnik koji je objavio oglas

    private Long priceAd;//cena koju je postavio u oglasu

    private Date dateEndOfBids;//datum isteka za prikupljanje ponuda

    public AdDTO(Long merchandiseId, Long userId, Long priceAd, Date dateEndOfBids) {
        this.merchandiseId = merchandiseId;
        this.userId = userId;
        this.priceAd = priceAd;
        this.dateEndOfBids = dateEndOfBids;
    }

    public AdDTO() {

    }

    public Long getMerchandiseId() {
        return merchandiseId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPriceAd() {
        return priceAd;
    }

    public Date getDateEndOfBids() {
        return dateEndOfBids;
    }

    public void setMerchandiseId(Long merchandiseId) {
        this.merchandiseId = merchandiseId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPriceAd(Long priceAd) {
        this.priceAd = priceAd;
    }

    public void setDateEndOfBids(Date dateEndOfBids) {
        this.dateEndOfBids = dateEndOfBids;
    }

    public Ad createAd(){
        Ad ad = new Ad();
        ad.setDateEndOfBids(dateEndOfBids);
        ad.setPriceAd(priceAd);
        ad.setBid(new ArrayList<>());
        return ad;
    }
    @Override
    public String toString() {
        return "AdDTO{" +
                "merchandiseId=" + merchandiseId +
                ", userId=" + userId +
                ", priceAd=" + priceAd +
                ", dateEndOfBids=" + dateEndOfBids +
                '}';
    }
}
