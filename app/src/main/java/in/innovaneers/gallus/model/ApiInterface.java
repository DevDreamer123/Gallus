package in.innovaneers.gallus.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("Farmers/Login") //Login
    Call<LoginResponseModel> login(@Body LoginRequestModel loginRequestModel);


    @POST("Farmers/Register")
    Call<RegistrationResponseModel> registration(@Body RegistrationRequestModel registrationRequestModel);

    @POST("Farms/Add")  //FarmAdd
    Call<RegistrationResponseModel> farmAdd(@Body FarmsModel farmsModel);

    @POST("Farms/List")  //farmList
    Call<List<FarmsModel>> farmList(@Body FarmerIdModel farmerIdModel);

    @POST("Batches/Add")  //batch
    Call<RegistrationResponseModel> addBatch(@Body BatchRequestModel batchRequestModel);


}
