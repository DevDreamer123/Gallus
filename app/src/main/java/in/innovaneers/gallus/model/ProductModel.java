package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class ProductModel {
    @SerializedName("ID")
    private String ID ;
    @SerializedName("ProductID")
    private String ProductID ;
    @SerializedName("Title")
    private String Title ;
    @SerializedName("Description")
    private String Description ;
    @SerializedName("MRP")
    private String MRP ;
    @SerializedName("Price")
    private String Price ;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMRP() {
        return MRP;
    }

    public void setMRP(String MRP) {
        this.MRP = MRP;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String productID) {
        ProductID = productID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
