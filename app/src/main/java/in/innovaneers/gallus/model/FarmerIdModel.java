package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class FarmerIdModel {
    @SerializedName("FarmerID")
    private String FarmerID;

    public FarmerIdModel(String farmerID) {
        FarmerID = farmerID;
    }

    public String getFarmerID() {
        return FarmerID;
    }

    public void setFarmerID(String farmerID) {
        FarmerID = farmerID;
    }
}
