package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class BatchRequestModel {
    @SerializedName("FarmID")
    private String FarmID ;
    @SerializedName("Chicks")
    private int Chicks;
    @SerializedName("FreeChicks ")
    private int FreeChicks ;
    @SerializedName("BodyWeight")
    private int BodyWeight ;
    @SerializedName("Date")
    private String Date ;
    @SerializedName("PurchaseRate")
    private double PurchaseRate;

    public BatchRequestModel(String farmID, int chicks, int freeChicks, int bodyWeight, String date, double PurchaseRate) {
        this.FarmID = farmID;
        this.Chicks = chicks;
        this.FreeChicks = freeChicks;
        this.BodyWeight = bodyWeight;
        this.Date = date;
        this.PurchaseRate = PurchaseRate;
    }
}
