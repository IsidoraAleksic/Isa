package com.example.demo.dto;

import com.example.demo.model.ReservationMerchandise;

public class ReservationMerchandiseDTO {

    private Long merchId;

    private Long userId;

    public ReservationMerchandiseDTO(){

    }

    public  ReservationMerchandiseDTO(Long merchId, Long userId){
        this.merchId = merchId;
        this.userId = userId;
    }

    public Long getMerchId() {
        return merchId;
    }

    public void setMerchId(Long merchId) {
        this.merchId = merchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
