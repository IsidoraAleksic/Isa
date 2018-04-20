package com.example.demo.dto;

public class CinemaTheatreRatingDTO {

    private short projectionGrade;

    private short ambientGrade;

    private Long projectionId;

    private Long ctId;

    private Long userId;


    public CinemaTheatreRatingDTO(){

    }

    public short getProjectionGrade() {
        return projectionGrade;
    }

    public void setProjectionGrade(short projectionGrade) {
        this.projectionGrade = projectionGrade;
    }

    public short getAmbientGrade() {
        return ambientGrade;
    }

    public void setAmbientGrade(short ambientGrade) {
        this.ambientGrade = ambientGrade;
    }

    public Long getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(Long projectionId) {
        this.projectionId = projectionId;
    }

    public Long getCtId() {
        return ctId;
    }

    public void setCtId(Long ctId) {
        this.ctId = ctId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CinemaTheatreRatingDTO{" +
                "projectionGrade=" + projectionGrade +
                ", ambientGrade=" + ambientGrade +
                ", projectionId=" + projectionId +
                ", ctId=" + ctId +
                ", userId=" + userId +
                '}';
    }


}
