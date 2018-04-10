package com.example.demo.dto;

import com.example.demo.model.Ad;
import com.example.demo.model.Merchandise;

import java.util.List;

public class MerchandiseDTO {

    private List<Ad> ads;
    private Long userId;
    private String nameMerchandise;
    private String description;
    private Long priceMerchandise;//ovo treba za blagajnu. kao neka realna cena
    private String imageMerchandise;

    public MerchandiseDTO() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public Merchandise createMerchandise(){
        Merchandise merch = new Merchandise();
        merch.setAds(ads);
        merch.setDescription(description);
        merch.setNameMerchandise(nameMerchandise);
        merch.setImageMerchandise(imageMerchandise);
        merch.setPriceMerchandise(priceMerchandise);

        return merch;
    }
    @Override
    public String toString() {
        return "Merchandise{" +
                ", nameMerchandise='" + nameMerchandise + '\'' +
                ", description='" + description + '\'' +
                ", priceMerchandise=" + priceMerchandise +
                ", imageMerchandise=" + imageMerchandise +
                '}';
    }

}
