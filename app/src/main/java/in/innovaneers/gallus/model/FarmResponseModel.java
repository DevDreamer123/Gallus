package in.innovaneers.gallus.model;

public class FarmResponseModel {
    private String Status;
    private String Description;
    private String FarmID;


    // Getter Methods

    public String getStatus() {
        return Status;
    }

    public String getDescription() {
        return Description;
    }

    public String getFarmID() {
        return FarmID;
    }

    // Setter Methods

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setFarmID(String FarmID) {
        this.FarmID = FarmID;
    }
}
