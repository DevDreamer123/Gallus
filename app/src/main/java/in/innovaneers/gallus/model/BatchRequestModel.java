package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class BatchRequestModel {
    @SerializedName("FarmID")
    private String FarmID ;
    @SerializedName("Chicks")
    private String Chicks;
    @SerializedName("FreeChicks ")
    private String FreeChicks ;
    @SerializedName("BodyWeight")
    private int BodyWeight ;
    @SerializedName("Date")
    private String Date ;
    @SerializedName("PurchaseRate")
    private String PurchaseRate;

    public BatchRequestModel(String farmID, String chicks, String freeChicks, int bodyWeight, String date, String PurchaseRate) {
        this.FarmID = farmID;
        this.Chicks = chicks;
        this.FreeChicks = freeChicks;
        this.BodyWeight = bodyWeight;
        this.Date = date;
        this.PurchaseRate = PurchaseRate;
    }
}
