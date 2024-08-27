package in.innovaneers.gallus;

import static kotlin.text.Typography.dagger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;

import in.innovaneers.gallus.model.ApiInterface;
import in.innovaneers.gallus.model.LoginRequestModel;
import in.innovaneers.gallus.model.LoginResponseModel;
import in.innovaneers.gallus.model.Repository;
import in.innovaneers.gallus.model.Result;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    TextView signup;
    Button sign_In_btn;
    EditText user_id_login , password_login;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    public static final String KEY_MOBILE = "Mobile";
    public static final String KEY_NAME = "Name";
    public static final String KEY_FARMER_ID = "ReferredId";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_ADDRESS = "Address";




 //   private  UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        user_id_login = findViewById(R.id.user_Id_login);
        password_login = findViewById(R.id.password_Login);

        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        sign_In_btn = findViewById(R.id.sign_in_btn);
        sign_In_btn.setOnClickListener(view -> {
                    if (TextUtils.isEmpty(user_id_login.getText().toString()) || TextUtils.isEmpty(password_login.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Username/Password Required", Toast.LENGTH_LONG).show();
                    } else {


                        LoginRequestModel namemobel = new LoginRequestModel(user_id_login.getText().toString(),password_login.getText().toString());



                        RetrofitInstance.BASEURL = " http://gallus.innovaneers.in/";
                        try {
                            Call<LoginResponseModel> lcall = RetrofitInstance.getInstance().getMyApi().login(namemobel);
                            lcall.enqueue(new Callback<LoginResponseModel>() {
                                @Override
                                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                                    // Toast.makeText(LoginActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                                    if (response.isSuccessful()) {
                                        LoginResponseModel loginResponse = response.body();
                                        SharedPreferences.Editor editor = shp.edit();
                                        editor.putString(KEY_FARMER_ID, response.body().getFarmerID());
                                        editor.putString(KEY_MOBILE, response.body().getMobile());
                                        editor.putString(KEY_NAME, response.body().getName());
                                        editor.putString(KEY_EMAIL, response.body().getEmail());
                                        editor.putString(KEY_PASSWORD, response.body().getPassword());
                                        editor.putString(KEY_ADDRESS,response.body().getAddress());
                                        //   editor.putString(KEY_USERTYPE,response.body().getUserType());
                                        editor.apply();
                                        // Toast.makeText(LoginActivity.this,response.body().getName(), Toast.LENGTH_SHORT).show();
                                        //  Toast.makeText(LoginActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(LoginActivity.this, PlanActivity.class);
                                        Toast.makeText(LoginActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                                    Toast.makeText(LoginActivity.this,t.toString(), Toast.LENGTH_SHORT).show();
                                    Log.d("error",t.getMessage());

                                    t.toString();

                                }
                            });

                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("error1",e.getMessage());
                            e.getMessage();
                        }

                    }







          /*  String username = user_id_login.getText().toString();
            String password = password_login.getText().toString();
            LoginRequestModel loginRequestModel = new LoginRequestModel(username,password);
            userViewModel.login(loginRequestModel).observe(this, new Observer<Result<LoginResponseModel>>() {
                @Override
                public void onChanged(Result<LoginResponseModel> loginResponseModelResult) {
                    if (loginResponseModelResult.getStatus() == Result.Status.LOADING){

                    } else if (loginResponseModelResult.getStatus() == Result.Status.SUCCESS) {

                       LoginResponseModel responseModel = loginResponseModelResult.getData();
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    } else if (loginResponseModelResult.getStatus() == Result.Status.ERROR ) {
                        Throwable error = loginResponseModelResult.getError();
                        Toast.makeText(LoginActivity.this, "Login failed:"+error.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }
            });*/

        });

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}