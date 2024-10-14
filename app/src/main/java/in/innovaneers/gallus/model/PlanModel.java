package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class PlanModel {
    @SerializedName("ID")
    private float ID;
    @SerializedName("PlanID")
    private String PlanID;
    @SerializedName("Title")
    private String Title;
    @SerializedName("Amount")
    private String Amount;
    @SerializedName("Validity")
    private String Validity;
    @SerializedName("isActive")
    private boolean isActive;


    // Getter Methods

    public float getID() {
        return ID;
    }

    public String getPlanID() {
        return PlanID;
    }

    public String getTitle() {
        return Title;
    }

    public String getAmount() {
        return Amount;
    }

    public String getValidity() {
        return Validity;
    }

    public boolean getIsActive() {
        return isActive;
    }

    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setPlanID(String PlanID) {
        this.PlanID = PlanID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public void setValidity(String Validity) {
        this.Validity = Validity;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }







    /*@SerializedName("ID")
    private float ID;
    @SerializedName("PlanID")
    private String PlanID;
    @SerializedName("Title")
    private String Title;
    @SerializedName("Amount")
    private String Amount;
    @SerializedName("Validity")
    private String Validity;
    @SerializedName("Mobiles")
    private String Mobiles;
    @SerializedName("CommissionForFreeMember")
    private float CommissionForFreeMember;
    @SerializedName("CommissionTypeFreeMember")
    private String CommissionTypeFreeMember;
    @SerializedName("CommissionForPaidMember")
    private float CommissionForPaidMember;
    @SerializedName("CommissionTypePaidMember")
    private String CommissionTypePaidMember;
    @SerializedName("Image")
    private int Image;

    public PlanModel(String title, String amount, String validity,int image) {
        Title = title;
        Amount = amount;
        Validity = validity;
        Image = image;
    }

    // Getter Methods

    public float getID() {
        return ID;
    }

    public String getPlanID() {
        return PlanID;
    }

    public String getTitle() {
        return Title;
    }

    public String getAmount() {
        return Amount;
    }

    public String getValidity() {
        return Validity;
    }

    public String getMobiles() {
        return Mobiles;
    }

    public float getCommissionForFreeMember() {
        return CommissionForFreeMember;
    }

    public String getCommissionTypeFreeMember() {
        return CommissionTypeFreeMember;
    }

    public float getCommissionForPaidMember() {
        return CommissionForPaidMember;
    }

    public String getCommissionTypePaidMember() {
        return CommissionTypePaidMember;
    }

    public int getImage() {
        return Image;
    }
    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setPlanID(String PlanID) {
        this.PlanID = PlanID;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setAmount(String  Amount) {
        this.Amount = Amount;
    }

    public void setValidity(String Validity) {
        this.Validity = Validity;
    }

    public void setMobiles(String Mobiles) {
        this.Mobiles = Mobiles;
    }

    public void setCommissionForFreeMember(float CommissionForFreeMember) {
        this.CommissionForFreeMember = CommissionForFreeMember;
    }

    public void setCommissionTypeFreeMember(String CommissionTypeFreeMember) {
        this.CommissionTypeFreeMember = CommissionTypeFreeMember;
    }

    public void setCommissionForPaidMember(float CommissionForPaidMember) {
        this.CommissionForPaidMember = CommissionForPaidMember;
    }

    public void setCommissionTypePaidMember(String CommissionTypePaidMember) {
        this.CommissionTypePaidMember = CommissionTypePaidMember;

    }

    public void setImage(int image) {
        Image = image;
    }*/
}
