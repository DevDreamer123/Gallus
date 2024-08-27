package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class DashBoardModel {
    @SerializedName("FCR")
    private String FCR;
    @SerializedName("CostPerKG")
    private String CostPerKG;
    @SerializedName("RatePerKG")
    private String RatePerKG;


    // Getter Methods

    public String getFCR() {
        return FCR;
    }

    public String getCostPerKG() {
        return CostPerKG;
    }

    public String getRatePerKG() {
        return RatePerKG;
    }

    // Setter Methods

    public void setFCR(String FCR) {
        this.FCR = FCR;
    }

    public void setCostPerKG(String CostPerKG) {
        this.CostPerKG = CostPerKG;
    }

    public void setRatePerKG(String RatePerKG) {
        this.RatePerKG = RatePerKG;
    }
}
