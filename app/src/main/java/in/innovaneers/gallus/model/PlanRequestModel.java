package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class PlanRequestModel {

    @SerializedName("FarmerID")
    private String FarmerID;
    @SerializedName("PlanID")
    private String PlanID;

    public PlanRequestModel(String farmerID, String planID) {
        FarmerID = farmerID;
        PlanID = planID;
    }

}
