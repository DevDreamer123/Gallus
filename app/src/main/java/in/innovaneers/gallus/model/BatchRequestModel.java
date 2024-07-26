package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class BatchRequestModel {
    @SerializedName("FarmID")
    private String FarmID ;
    @SerializedName("Chicks")
    private String Chicks;
    @SerializedName("Purchase")
    private String Purchase;

    public BatchRequestModel(String FarmID, String Chicks, String Purchase) {
        this.FarmID = FarmID;
        this.Chicks = Chicks;
        this.Purchase = Purchase;
    }

}
