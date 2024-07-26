package in.innovaneers.gallus.model;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {
    @SerializedName("Mobile")
    private String Mobile;
    @SerializedName("Password")
    private String Password;

    public LoginRequestModel(String Mobile, String Password) {
        this.Mobile = Mobile;
        this.Password = Password;
    }

}
