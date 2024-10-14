package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class DailyRecordHistoryModel {
    @SerializedName("ID")
    private float ID;
    @SerializedName("BatchID")
    private String BatchID;
    @SerializedName("Housed")
    private String Housed;
    @SerializedName("Age")
    private String Age;
    @SerializedName("MortalityCount")
    private String MortalityCount;
    @SerializedName("MortalityTotal")
    private String MortalityTotal;
    @SerializedName("MortalityPercentage")
    private String MortalityPercentage;
    @SerializedName("FeedBrand")
    private String FeedBrand ;
    @SerializedName("FeedConsumption")
    private String FeedConsumption;
    @SerializedName("CumulativeFeed")
    private String CumulativeFeed;
    @SerializedName("BodyWeight")
    private String BodyWeight;
    @SerializedName("DayGain")
    private String DayGain;
    @SerializedName("FCR")
    private String FCR;
    @SerializedName("Medicine")
    private String Medicine ;
    @SerializedName("Vaccine")
    private String Vaccine ;
    @SerializedName("WaterConsumption")
    private String WaterConsumption ;
    @SerializedName("Date")
    private String Date ;
    @SerializedName("StandardFeedConsumption")
    private String StandardFeedConsumption;
    @SerializedName("StandardCumulativeFeed")
    private String StandardCumulativeFeed;
    @SerializedName("StandardBodyWeight")
    private String StandardBodyWeight;
    @SerializedName("StandardDayGain")
    private String StandardDayGain;
    @SerializedName("StandardFCR")
    private String StandardFCR;


    // Getter Methods

    public float getID() {
        return ID;
    }

    public String getBatchID() {
        return BatchID;
    }

    public String getHoused() {
        return Housed;
    }

    public String getAge() {
        return Age;
    }

    public String getMortalityCount() {
        return MortalityCount;
    }

    public String getMortalityTotal() {
        return MortalityTotal;
    }

    public String getMortalityPercentage() {
        return MortalityPercentage;
    }

    public String getFeedBrand() {
        return FeedBrand;
    }

    public String getFeedConsumption() {
        return FeedConsumption;
    }

    public String getCumulativeFeed() {
        return CumulativeFeed;
    }

    public String getBodyWeight() {
        return BodyWeight;
    }

    public String getDayGain() {
        return DayGain;
    }

    public String getFCR() {
        return FCR;
    }

    public String getMedicine() {
        return Medicine;
    }

    public String getVaccine() {
        return Vaccine;
    }

    public String getWaterConsumption() {
        return WaterConsumption;
    }

    public String getDate() {
        return Date;
    }

    public String getStandardFeedConsumption() {
        return StandardFeedConsumption;
    }

    public String getStandardCumulativeFeed() {
        return StandardCumulativeFeed;
    }

    public String getStandardBodyWeight() {
        return StandardBodyWeight;
    }

    public String getStandardDayGain() {
        return StandardDayGain;
    }

    public String getStandardFCR() {
        return StandardFCR;
    }

    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setBatchID(String BatchID) {
        this.BatchID = BatchID;
    }

    public void setHoused(String Housed) {
        this.Housed = Housed;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setMortalityCount(String MortalityCount) {
        this.MortalityCount = MortalityCount;
    }

    public void setMortalityTotal(String MortalityTotal) {
        this.MortalityTotal = MortalityTotal;
    }

    public void setMortalityPercentage(String MortalityPercentage) {
        this.MortalityPercentage = MortalityPercentage;
    }

    public void setFeedBrand(String FeedBrand) {
        this.FeedBrand = FeedBrand;
    }

    public void setFeedConsumption(String FeedConsumption) {
        this.FeedConsumption = FeedConsumption;
    }

    public void setCumulativeFeed(String CumulativeFeed) {
        this.CumulativeFeed = CumulativeFeed;
    }

    public void setBodyWeight(String BodyWeight) {
        this.BodyWeight = BodyWeight;
    }

    public void setDayGain(String DayGain) {
        this.DayGain = DayGain;
    }

    public void setFCR(String FCR) {
        this.FCR = FCR;
    }

    public void setMedicine(String Medicine) {
        this.Medicine = Medicine;
    }

    public void setVaccine(String Vaccine) {
        this.Vaccine = Vaccine;
    }

    public void setWaterConsumption(String WaterConsumption) {
        this.WaterConsumption = WaterConsumption;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public void setStandardFeedConsumption(String StandardFeedConsumption) {
        this.StandardFeedConsumption = StandardFeedConsumption;
    }

    public void setStandardCumulativeFeed(String StandardCumulativeFeed) {
        this.StandardCumulativeFeed = StandardCumulativeFeed;
    }

    public void setStandardBodyWeight(String StandardBodyWeight) {
        this.StandardBodyWeight = StandardBodyWeight;
    }

    public void setStandardDayGain(String StandardDayGain) {
        this.StandardDayGain = StandardDayGain;
    }

    public void setStandardFCR(String StandardFCR) {
        this.StandardFCR = StandardFCR;
    }
}
