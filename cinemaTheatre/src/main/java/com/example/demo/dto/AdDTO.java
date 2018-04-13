package com.example.demo.dto;

import com.example.demo.model.Ad;

import java.util.ArrayList;
import java.util.Date;

public class AdDTO {

    private String nameAd;

    private String description;

    private String imageAd;

    private Long userId;//korisnik koji je objavio oglas

    private Long priceAd;//cena koju je postavio u oglasu

    private Date dateEndOfBids;//datum isteka za prikupljanje ponuda

    public AdDTO(String nameAd, String description,String imageAd, Long userId, Long priceAd, Date dateEndOfBids) {
        this.nameAd = nameAd;
        this.description = description;
        this.imageAd = imageAd;
        this.userId = userId;
        this.priceAd = priceAd;
        this.dateEndOfBids = dateEndOfBids;
    }

    public AdDTO() {

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

    public Long getUserId() {
        return userId;
    }

    public Long getPriceAd() {
        return priceAd;
    }

    public Date getDateEndOfBids() {
        return dateEndOfBids;
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

    public String getImageAd() {
        return imageAd;
    }

    public void setImageAd(String imageAd) {
        this.imageAd = imageAd;
    }

    public Ad createAd(){
        Ad ad = new Ad();
        ad.setNameAd(nameAd);
        ad.setImageAd(imageAd);
        ad.setDateEndOfBids(dateEndOfBids);
        ad.setPriceAd(priceAd);
        ad.setDescription(description);
        ad.setBid(new ArrayList<>());
        return ad;
    }


    @Override
    public String toString() {
        return "AdDTO{" +
                "nameAd='" + nameAd + '\'' +
                ", description='" + description + '\'' +
                ", imageAd='" + imageAd + '\'' +
                ", userId=" + userId +
                ", priceAd=" + priceAd +
                ", dateEndOfBids=" + dateEndOfBids +
                '}';
    }
}