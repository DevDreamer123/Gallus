package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class PlanPurchaseResponseModel {
    @SerializedName("Status")
    private String Status;
    @SerializedName("Description")
    private String Description;


    // Getter Methods

    public String getStatus() {
        return Status;
    }

    public String getDescription() {
        return Description;
    }

    // Setter Methods

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
