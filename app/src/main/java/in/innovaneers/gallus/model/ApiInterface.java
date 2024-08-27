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

<<<<<<< HEAD
=======
    @POST("Batches/List")  //batchList
    Call<List<BatchListModel>> ListBatch(@Body FarmIdModel farmIdModel);

    @POST("Records/History")  //RecordHistory
    Call<List<RecordRequestModel>> recordsHistory(@Body BatchIdModel batchIdModel);

    @POST("Records/Add")  //Add Record
    Call<RecordResponseModel> AddRecord(@Body RecordRequestModel recordRequestModel);

    @POST("Batches/Dashboard")  //DashBoard
    Call<DashBoardModel> dashBoard(@Body BatchIdModel batchIdModel);

    @POST("Farms/Details")  //CurrentBatchIDGet
    Call<GetBatchIDModel> GetBatchId(@Body FarmIdModel farmIdModel);

>>>>>>> ef3c6549b89830f91825af27b024f24cc9ef2d1f

}
