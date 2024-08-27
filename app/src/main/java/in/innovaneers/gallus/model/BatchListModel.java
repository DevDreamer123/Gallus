package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class BatchListModel {
    @SerializedName("ID")
    private float ID;
    @SerializedName("BatchID")
    private String BatchID;
    @SerializedName("FarmID")
    private String FarmID = null;
    @SerializedName("Chicks")
    private String Chicks = null;
    @SerializedName("FreeChicks")
    private String FreeChicks = null;
    @SerializedName("FeedingCost")
    private float FeedingCost;
    @SerializedName("TotalCost")
    private String TotalCost;
    @SerializedName("TotalMortality")
    private float TotalMortality;
    @SerializedName("PurchaseRate")
    private float PurchaseRate;
    @SerializedName("Date")
    private String Date = null;
    @SerializedName("FeedConsumption")
    private String FeedConsumption = null;
    @SerializedName("WaterConsumption")
    private String WaterConsumption = null;
    @SerializedName("Medicine")
    private String Medicine = null;
    @SerializedName("Vaccine")
    private String Vaccine = null;
    @SerializedName("BodyWeight")
    private String BodyWeight = null;


    // Getter Methods

    public float getID() {
        return ID;
    }

    public String getBatchID() {
        return BatchID;
    }

    public String getFarmID() {
        return FarmID;
    }

    public String getChicks() {
        return Chicks;
    }

    public String getFreeChicks() {
        return FreeChicks;
    }

    public float getFeedingCost() {
        return FeedingCost;
    }

    public String getTotalCost() {
        return TotalCost;
    }

    public float getTotalMortality() {
        return TotalMortality;
    }

    public float getPurchaseRate() {
        return PurchaseRate;
    }

    public String getDate() {
        return Date;
    }

    public String getFeedConsumption() {
        return FeedConsumption;
    }

    public String getWaterConsumption() {
        return WaterConsumption;
    }

    public String getMedicine() {
        return Medicine;
    }

    public String getVaccine() {
        return Vaccine;
    }

    public String getBodyWeight() {
        return BodyWeight;
    }

    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setBatchID(String BatchID) {
        this.BatchID = BatchID;
    }

    public void setFarmID(String FarmID) {
        this.FarmID = FarmID;
    }

    public void setChicks(String Chicks) {
        this.Chicks = Chicks;
    }

    public void setFreeChicks(String FreeChicks) {
        this.FreeChicks = FreeChicks;
    }

    public void setFeedingCost(float FeedingCost) {
        this.FeedingCost = FeedingCost;
    }

    public void setTotalCost(String TotalCost) {
        this.TotalCost = TotalCost;
    }

    public void setTotalMortality(float TotalMortality) {
        this.TotalMortality = TotalMortality;
    }

    public void setPurchaseRate(float PurchaseRate) {
        this.PurchaseRate = PurchaseRate;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setFeedConsumption(String FeedConsumption) {
        this.FeedConsumption = FeedConsumption;
    }

    public void setWaterConsumption(String WaterConsumption) {
        this.WaterConsumption = WaterConsumption;
    }

    public void setMedicine(String Medicine) {
        this.Medicine = Medicine;
    }

    public void setVaccine(String Vaccine) {
        this.Vaccine = Vaccine;
    }

    public void setBodyWeight(String BodyWeight) {
        this.BodyWeight = BodyWeight;
    }
}
