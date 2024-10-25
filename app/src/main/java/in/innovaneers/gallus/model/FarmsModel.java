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
    private int Chicks;
    @SerializedName("Size")
    private String Size;
    @SerializedName("FreeChicks ")
    private int FreeChicks ;
    @SerializedName("BodyWeight")
    private int BodyWeight ;
    @SerializedName("Date")
    private String Date ;
    @SerializedName("PurchaseAmount")
    private double PurchaseAmount;

    public FarmsModel( String farmerID, String name, String address, String area, String city, String state, String pincode, int chicks, String size, int freeChicks, int bodyWeight, String date, double purchaseAmount) {
        FarmerID = farmerID;
        Name = name;
        Address = address;
        Area = area;
        City = city;
        State = state;
        Pincode = pincode;
        Chicks = chicks;
        Size = size;
        FreeChicks = freeChicks;
        BodyWeight = bodyWeight;
        Date = date;
        PurchaseAmount = purchaseAmount;
    }


    public float getID() {
        return ID;
    }

    public void setID(float ID) {
        this.ID = ID;
    }

    public String getFarmID() {
        return FarmID;
    }

    public void setFarmID(String farmID) {
        FarmID = farmID;
    }

    public String getFarmerID() {
        return FarmerID;
    }

    public void setFarmerID(String farmerID) {
        FarmerID = farmerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public int getChicks() {
        return Chicks;
    }

    public void setChicks(int chicks) {
        Chicks = chicks;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public int getFreeChicks() {
        return FreeChicks;
    }

    public void setFreeChicks(int freeChicks) {
        FreeChicks = freeChicks;
    }

    public int getBodyWeight() {
        return BodyWeight;
    }

    public void setBodyWeight(int bodyWeight) {
        BodyWeight = bodyWeight;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getPurchaseAmount() {
        return PurchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        PurchaseAmount = purchaseAmount;
    }
}
