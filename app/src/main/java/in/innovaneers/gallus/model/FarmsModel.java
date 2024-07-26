package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class FarmsModel {
    @SerializedName("ID")
    private float ID;
    @SerializedName("FarmID")
    private String FarmID;
    @SerializedName("FarmerID")
    private String FarmerID;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Address")
    private String Address;
    @SerializedName("Area")
    private String Area;
    @SerializedName("City")
    private String City;
    @SerializedName("State")
    private String State;
    @SerializedName("Pincode")
    private String Pincode;
    @SerializedName("Chicks")
    private String Chicks;
    @SerializedName("Size")
    private String Size;


    // Getter Methods

    public float getID() {
        return ID;
    }

    public String getFarmID() {
        return FarmID;
    }

    public String getFarmerID() {
        return FarmerID;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getArea() {
        return Area;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getPincode() {
        return Pincode;
    }

    public String getChicks() {
        return Chicks;
    }

    public String getSize() {
        return Size;
    }
    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setFarmID(String FarmID) {
        this.FarmID = FarmID;
    }

    public void setFarmerID(String FarmerID) {
        this.FarmerID = FarmerID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setPincode(String Pincode) {
        this.Pincode = Pincode;
    }

    public void setChicks(String Chicks) {
        this.Chicks = Chicks;
    }

    public void setSize(String size) {
        Size = size;
    }
}
