package in.innovaneers.gallus.model;

import androidx.lifecycle.LiveData;
import in.innovaneers.gallus.model.LoginResponseModel;
import retrofit2.Call;


public interface Repository  {
 //   private final ApiInterface apiInterface;

   // public Repository(ApiInterface apiInterface) {
      //  this.apiInterface = apiInterface;
  //  }

     LiveData<Result<LoginResponseModel>> login(LoginRequestModel loginRequestModel);
     LiveData<Result<RegistrationResponseModel>> register(RegistrationRequestModel registrationRequestModel);

    //    LoginRequestModel requestModel = new LoginRequestModel(mobile, password);
    //    Call<LoginResponseModel> call = apiInterface.login(requestModel);
    //    return makeApiCall(call);
   // }
}

