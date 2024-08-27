package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class RecordRequestModel {
    @SerializedName("Id")
    private String Id;
    @SerializedName("BatchID")
    private String BatchID ;
    @SerializedName("Age")
    private String Age ;
    @SerializedName("Date")
    private String Date;
    @SerializedName("MortalityCount")
    private String MortalityCount ;
    @SerializedName("MortalityTotal")
    private String MortalityTotal ;
    @SerializedName("MortalityPercentage")
    private String MortalityPercentage ;
    @SerializedName("FeedBrand")
    private String FeedBrand ;
    @SerializedName("FeedConsumption")
    private String FeedConsumption ;
    @SerializedName("CumulativeFeed")
    private String CumulativeFeed ;
    @SerializedName("BodyWeight")
    private String BodyWeight ;
    @SerializedName("FCR")
    private String FCR;
    @SerializedName("Medicine")
    private String Medicine;
    @SerializedName("Vaccine")
    private String Vaccine;
    @SerializedName("WaterConsumption")
    private String WaterConsumption;


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getBatchID() {
        return BatchID;
    }

    public void setBatchID(String batchID) {
        BatchID = batchID;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getMortalityCount() {
        return MortalityCount;
    }

    public void setMortalityCount(String mortalityCount) {
        MortalityCount = mortalityCount;
    }

    public String getMortalityTotal() {
        return MortalityTotal;
    }

    public void setMortalityTotal(String mortalityTotal) {
        MortalityTotal = mortalityTotal;
    }

    public String getMortalityPercentage() {
        return MortalityPercentage;
    }

    public void setMortalityPercentage(String mortalityPercentage) {
        MortalityPercentage = mortalityPercentage;
    }

    public String getFeedBrand() {
        return FeedBrand;
    }

    public void setFeedBrand(String feedBrand) {
        FeedBrand = feedBrand;
    }

    public String getFeedConsumption() {
        return FeedConsumption;
    }

    public void setFeedConsumption(String feedConsumption) {
        FeedConsumption = feedConsumption;
    }

    public String getCumulativeFeed() {
        return CumulativeFeed;
    }

    public void setCumulativeFeed(String cumulativeFeed) {
        CumulativeFeed = cumulativeFeed;
    }

    public String getBodyWeight() {
        return BodyWeight;
    }

    public void setBodyWeight(String bodyWeight) {
        BodyWeight = bodyWeight;
    }

    public String getFCR() {
        return FCR;
    }

    public void setFCR(String FCR) {
        this.FCR = FCR;
    }

    public String getMedicine() {
        return Medicine;
    }

    public void setMedicine(String medicine) {
        Medicine = medicine;
    }

    public String getVaccine() {
        return Vaccine;
    }

    public void setVaccine(String vaccine) {
        Vaccine = vaccine;
    }

    public String getWaterConsumption() {
        return WaterConsumption;
    }

    public void setWaterConsumption(String waterConsumption) {
        WaterConsumption = waterConsumption;
    }
}
