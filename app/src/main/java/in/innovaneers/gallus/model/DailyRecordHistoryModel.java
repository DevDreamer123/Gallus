package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class DailyRecordHistoryModel {
    @SerializedName("ID")
    private float ID;
    @SerializedName("BatchID")
    private String BatchID;
    @SerializedName("Housed")
    private float Housed;
    @SerializedName("Age")
    private String Age;
    @SerializedName("MortalityCount")
    private float MortalityCount;
    @SerializedName("MortalityTotal")
    private float MortalityTotal;
    @SerializedName("MortalityPercentage")
    private float MortalityPercentage;
    @SerializedName("FeedBrand")
    private String FeedBrand ;
    @SerializedName("FeedConsumption")
    private float FeedConsumption;
    @SerializedName("CumulativeFeed")
    private float CumulativeFeed;
    @SerializedName("BodyWeight")
    private float BodyWeight;
    @SerializedName("DayGain")
    private float DayGain;
    @SerializedName("FCR")
    private float FCR;
    @SerializedName("Medicine")
    private String Medicine ;
    @SerializedName("Vaccine")
    private String Vaccine ;
    @SerializedName("WaterConsumption")
    private String WaterConsumption ;
    @SerializedName("Date")
    private String Date ;
    @SerializedName("StandardFeedConsumption")
    private float StandardFeedConsumption;
    @SerializedName("StandardCumulativeFeed")
    private float StandardCumulativeFeed;
    @SerializedName("StandardBodyWeight")
    private float StandardBodyWeight;
    @SerializedName("StandardDayGain")
    private float StandardDayGain;
    @SerializedName("StandardFCR")
    private float StandardFCR;


    // Getter Methods

    public float getID() {
        return ID;
    }

    public String getBatchID() {
        return BatchID;
    }

    public float getHoused() {
        return Housed;
    }

    public String getAge() {
        return Age;
    }

    public float getMortalityCount() {
        return MortalityCount;
    }

    public float getMortalityTotal() {
        return MortalityTotal;
    }

    public float getMortalityPercentage() {
        return MortalityPercentage;
    }

    public String getFeedBrand() {
        return FeedBrand;
    }

    public float getFeedConsumption() {
        return FeedConsumption;
    }

    public float getCumulativeFeed() {
        return CumulativeFeed;
    }

    public float getBodyWeight() {
        return BodyWeight;
    }

    public float getDayGain() {
        return DayGain;
    }

    public float getFCR() {
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

    public float getStandardFeedConsumption() {
        return StandardFeedConsumption;
    }

    public float getStandardCumulativeFeed() {
        return StandardCumulativeFeed;
    }

    public float getStandardBodyWeight() {
        return StandardBodyWeight;
    }

    public float getStandardDayGain() {
        return StandardDayGain;
    }

    public float getStandardFCR() {
        return StandardFCR;
    }

    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setBatchID(String BatchID) {
        this.BatchID = BatchID;
    }

    public void setHoused(float Housed) {
        this.Housed = Housed;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public void setMortalityCount(float MortalityCount) {
        this.MortalityCount = MortalityCount;
    }

    public void setMortalityTotal(float MortalityTotal) {
        this.MortalityTotal = MortalityTotal;
    }

    public void setMortalityPercentage(float MortalityPercentage) {
        this.MortalityPercentage = MortalityPercentage;
    }

    public void setFeedBrand(String FeedBrand) {
        this.FeedBrand = FeedBrand;
    }

    public void setFeedConsumption(float FeedConsumption) {
        this.FeedConsumption = FeedConsumption;
    }

    public void setCumulativeFeed(float CumulativeFeed) {
        this.CumulativeFeed = CumulativeFeed;
    }

    public void setBodyWeight(float BodyWeight) {
        this.BodyWeight = BodyWeight;
    }

    public void setDayGain(float DayGain) {
        this.DayGain = DayGain;
    }

    public void setFCR(float FCR) {
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

    public void setStandardFeedConsumption(float StandardFeedConsumption) {
        this.StandardFeedConsumption = StandardFeedConsumption;
    }

    public void setStandardCumulativeFeed(float StandardCumulativeFeed) {
        this.StandardCumulativeFeed = StandardCumulativeFeed;
    }

    public void setStandardBodyWeight(float StandardBodyWeight) {
        this.StandardBodyWeight = StandardBodyWeight;
    }

    public void setStandardDayGain(float StandardDayGain) {
        this.StandardDayGain = StandardDayGain;
    }

    public void setStandardFCR(float StandardFCR) {
        this.StandardFCR = StandardFCR;
    }
}
