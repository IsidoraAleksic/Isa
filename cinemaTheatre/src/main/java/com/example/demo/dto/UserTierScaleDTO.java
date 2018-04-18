package com.example.demo.dto;

public class UserTierScaleDTO {
    private int minBronze;

    private int minSilver;

    private int minGolden;

    public int getMinBronze() {
        return minBronze;
    }

    public void setMinBronze(int minBronze) {
        this.minBronze = minBronze;
    }

    public int getMinSilver() {
        return minSilver;
    }

    public void setMinSilver(int minSilver) {
        this.minSilver = minSilver;
    }

    public int getMinGolden() {
        return minGolden;
    }

    public void setMinGolden(int minGolden) {
        this.minGolden = minGolden;
    }

    public UserTierScaleDTO() {

    }

    public UserTierScaleDTO(int minBronze, int minSilver, int minGolden) {
        this.minBronze = minBronze;
        this.minSilver = minSilver;
        this.minGolden = minGolden;
    }

    public String toString() {
        return "UserTierScaleDTO{" +
                "minBronze=" + minBronze +
                ", minSilver=" + minSilver +
                ", minGolden=" + minGolden +
                '}';
    }
}
