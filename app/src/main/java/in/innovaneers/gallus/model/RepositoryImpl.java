package in.innovaneers.gallus.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import retrofit2.Call;
import in.innovaneers.gallus.model.ApiInterface;
import in.innovaneers.gallus.model.Result;

import retrofit2.Callback;
import retrofit2.Response;

/*public class RepositoryImpl implements Repository{

private  final ApiInterface apiInterface;
//@Inject
public RepositoryImpl(ApiInterface apiInterface){
   this.apiInterface = apiInterface;
}
    public LiveData<Result<LoginResponseModel>> login(LoginRequestModel loginRequestModel) {
        MutableLiveData<Result<LoginResponseModel>> result = new MutableLiveData<>();
        result.setValue(Result.loading());
        apiInterface.login(loginRequestModel).enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    //result.setValue(new Result.Success<>(response.body()));
                    result.setValue(Result.success(response.body()));
                } else {
                    result.setValue(Result.error(new Exception("Api call failed")));
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                result.setValue(Result.error(t));

            }
        });
        return result;
    }
}*/
