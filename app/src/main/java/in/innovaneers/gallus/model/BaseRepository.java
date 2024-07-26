package in.innovaneers.gallus.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*public class BaseRepository {

//Login Method
    protected <T> LiveData<Result<T>> makeApiCall(Call<T> call){
        MutableLiveData<Result<T>> result = new MutableLiveData<>();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.isSuccessful() && response.body() != null){
                    //result.setValue(new Result.Success<>(response.body()));
                    result.setValue(new Result.Success<>(response.body()));
                }else {
                    result.setValue(new Result.Error<>(new Exception("Api call failed")));
                }
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                result.setValue(new Result.Error<>(t));

            }
        });
        return result;
    }
}*/
