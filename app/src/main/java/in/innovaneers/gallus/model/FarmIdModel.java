package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class FarmIdModel {
    @SerializedName("FarmID")
    private String FarmID;

    public FarmIdModel(String farmID) {
        FarmID = farmID;
    }

    public String getFarmID() {
        return FarmID;
    }

    public void setFarmID(String farmID) {
        FarmID = farmID;
    }
}
