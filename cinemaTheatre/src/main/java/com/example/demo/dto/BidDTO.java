package com.example.demo.dto;

import com.example.demo.model.Bid;
import com.example.demo.model.AdBidStatus;

public class BidDTO {


    private Long adId;

    private Long idGuestBid;

    private Long priceBid;

    public BidDTO() {

    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getIdGuestBid() {
        return idGuestBid;
    }

    public Long getPriceBid() {
        return priceBid;
    }

    public void setIdGuestBid(Long idGuestBid) {
        this.idGuestBid = idGuestBid;
    }

    public void setPriceBid(Long priceBid) {
        this.priceBid = priceBid;
    }


    public Bid createBid() {
        Bid bid = new Bid();
        bid.setPriceBid(priceBid);
        bid.setAdBidStatus(AdBidStatus.WAITING);
        return bid;
    }

    @Override
    public String toString() {
        return "Bid{" +
                ", adId=" + adId +
                ", idGuestBid=" + idGuestBid +
                ", priceBid=" + priceBid +
                '}';
    }
}
