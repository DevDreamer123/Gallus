package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class BatchRequestModel {
    @SerializedName("FarmID")
    private String FarmID ;
    @SerializedName("Chicks")
    private String Chicks;
    @SerializedName("FreeChicks ")
    private String FreeChicks ;
    @SerializedName("Date")
    private String Date ;
    @SerializedName("Purchase")
    private String Purchase;

    public BatchRequestModel(String FarmID, String Chicks, String Purchase) {
        this.FarmID = FarmID;
        this.Chicks = Chicks;
        this.Purchase = Purchase;
    }

    public BatchRequestModel(String farmID, String chicks, String freeChicks, String date, String purchase) {
        FarmID = farmID;
        Chicks = chicks;
        FreeChicks = freeChicks;
        Date = date;
        Purchase = purchase;
    }
}
