package in.innovaneers.gallus.model;

import android.util.Log;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//@Module
//@InstallIn(SingletonComponent.class)
public class RetrofitInstance {

    private static RetrofitInstance instance = null;
    public static String BASEURL;
    private ApiInterface myApi;


    private RetrofitInstance() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Log.i("BaseURl",BASEURL);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(ApiInterface.class);

    }

    public static synchronized RetrofitInstance getInstance() {//login
        if (instance == null) {

            instance = new RetrofitInstance();
        }
        return instance;
    }

    public ApiInterface getMyApi() {
        return myApi;
    }







   /* @Provides
    @Singleton
    public static Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://gallus.innovaneers.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static ApiInterface provideApiInterface(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }*/
}
