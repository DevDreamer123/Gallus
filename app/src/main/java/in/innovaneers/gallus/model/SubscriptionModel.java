package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class SubscriptionModel {
    @SerializedName("ID")
    private float ID;
    @SerializedName("SubscriptionID")
    private String SubscriptionID;
    @SerializedName("OrderID")
    private String OrderID;
    @SerializedName("FarmerID")
    private String FarmerID;
    @SerializedName("PlanID")
    private String PlanID;
    @SerializedName("Amount")
    private float Amount;
    @SerializedName("ValidTill")
    private String ValidTill;
    @SerializedName("SubscriptionDate")
    private String SubscriptionDate;


    // Getter Methods

    public float getID() {
        return ID;
    }

    public String getSubscriptionID() {
        return SubscriptionID;
    }

    public String getOrderID() {
        return OrderID;
    }

    public String getFarmerID() {
        return FarmerID;
    }

    public String getPlanID() {
        return PlanID;
    }

    public float getAmount() {
        return Amount;
    }

    public String getValidTill() {
        return ValidTill;
    }

    public String getSubscriptionDate() {
        return SubscriptionDate;
    }

    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setSubscriptionID(String SubscriptionID) {
        this.SubscriptionID = SubscriptionID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public void setFarmerID(String FarmerID) {
        this.FarmerID = FarmerID;
    }

    public void setPlanID(String PlanID) {
        this.PlanID = PlanID;
    }

    public void setAmount(float Amount) {
        this.Amount = Amount;
    }

    public void setValidTill(String ValidTill) {
        this.ValidTill = ValidTill;
    }

    public void setSubscriptionDate(String SubscriptionDate) {
        this.SubscriptionDate = SubscriptionDate;
    }
}
