package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {
    @SerializedName("ID")
    private float ID;
    @SerializedName("FarmerID")
    private String FarmerID;
    @SerializedName("Name")
    private String Name;
    @SerializedName("Mobile")
    private String Mobile;
    @SerializedName("Email")
    private String Email;
    @SerializedName("Password")
    private String Password;
    @SerializedName("Address")
    private String Address;
    @SerializedName("Area")
    private String Area;
    @SerializedName("City")
    private String City;
    @SerializedName("RegistrationDate")
    private String RegistrationDate;


    // Getter Methods

    public float getID() {
        return ID;
    }

    public String getFarmerID() {
        return FarmerID;
    }

    public String getName() {
        return Name;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
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

    public String getRegistrationDate() {
        return RegistrationDate;
    }

    // Setter Methods

    public void setID(float ID) {
        this.ID = ID;
    }

    public void setFarmerID(String FarmerID) {
        this.FarmerID = FarmerID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
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

    public void setRegistrationDate(String RegistrationDate) {
        this.RegistrationDate = RegistrationDate;
    }
}
