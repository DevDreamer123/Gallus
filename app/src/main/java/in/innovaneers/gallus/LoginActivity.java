package in.innovaneers.gallus;

import static kotlin.text.Typography.dagger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;

import in.innovaneers.gallus.model.ApiInterface;
import in.innovaneers.gallus.model.FarmerIdModel;
import in.innovaneers.gallus.model.LoginRequestModel;
import in.innovaneers.gallus.model.LoginResponseModel;
import in.innovaneers.gallus.model.RegistrationResponseModel;
import in.innovaneers.gallus.model.Repository;
import in.innovaneers.gallus.model.Result;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    TextView signup;
    Button sign_In_btn;
    EditText user_id_login, password_login;
    public static SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    public static final String KEY_MOBILE = "Mobile";
    public static final String KEY_NAME = "Name";
    public static final String KEY_FARMER_ID = "ReferredId";
    public static final String KEY_EMAIL = "Email";
    public static final String KEY_PASSWORD = "Password";
    public static final String KEY_ADDRESS = "Address";

    private ImageView ivShowHidePassword;
    private boolean isPasswordVisible = false;
    ProgressBar progressBarLog;


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


        ivShowHidePassword = findViewById(R.id.ivShowHidePassword);


        ivShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide the password
                    password_login.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivShowHidePassword.setImageResource(R.drawable.baseline_visibility_off_24); // Change icon to visibility_off
                } else {
                    // Show the password
                    password_login.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivShowHidePassword.setImageResource(R.drawable.baseline_visibility_24); // Change icon to visibility
                }
                isPasswordVisible = !isPasswordVisible;

                // Move cursor to the end of the text
                password_login.setSelection(password_login.getText().length());
            }
        });


        user_id_login = findViewById(R.id.user_Id_login);
        password_login = findViewById(R.id.password_Login);
        progressBarLog = findViewById(R.id.progressBarLog);
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        sign_In_btn = findViewById(R.id.sign_in_btn);
        sign_In_btn.setOnClickListener(view -> {
            // Show a loading indicator to inform the user
            // progressBarLog.setVisibility(View.VISIBLE);  // Progress bar to show process is running
            if (TextUtils.isEmpty(user_id_login.getText().toString()) || TextUtils.isEmpty(password_login.getText().toString())) {
                Toast.makeText(LoginActivity.this, "Username/Password Required", Toast.LENGTH_LONG).show();
            } else {


                LoginRequestModel namemobel = new LoginRequestModel(user_id_login.getText().toString(), password_login.getText().toString());


                RetrofitInstance.BASEURL = "http://api.gallus.in/";
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
                                editor.putString(KEY_ADDRESS, response.body().getAddress());
                                //   editor.putString(KEY_USERTYPE,response.body().getUserType());
                                editor.apply();
                                checkPlanValidity(loginResponse.getFarmerID());
                                // Toast.makeText(LoginActivity.this,response.body().getName(), Toast.LENGTH_SHORT).show();
                                //  Toast.makeText(LoginActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();
                              //  Intent i = new Intent(LoginActivity.this, PlanActivity.class);
                                //i.putExtra("FarmerIDLogin", response.body().getFarmerID());
                                Toast.makeText(LoginActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();
                               // progressBarLog.setVisibility(View.GONE);
                               // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                               // startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                            Log.d("error", t.getMessage());

                            t.toString();

                        }
                    });

                } catch (Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("error1", e.getMessage());
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
                Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


    // Method to check plan validity via API after login
    private void checkPlanValidity(String farmerId) {
        FarmerIdModel farmerIdModel = new FarmerIdModel(farmerId);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";

        Call<RegistrationResponseModel> call = RetrofitInstance.getInstance().getMyApi().createValidPlan(farmerIdModel);
        call.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegistrationResponseModel planValidity = response.body();

                    // Check if the plan is valid
                    if ("Success".equals(planValidity.getStatus())) {
                        // Plan is valid, proceed to check farm quantity
                        checkFarmQty();
                    } else {
                        // Plan is not valid, navigate to PlanActivity
                        startActivity(new Intent(LoginActivity.this, PlanActivity.class));
                        finish();
                    }
                } else {
                    // Failed to check plan validity
                    Toast.makeText(LoginActivity.this, "Failed to check plan validity", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, PlanActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                // Handle API failure
                Toast.makeText(LoginActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, PlanActivity.class));
                finish();
            }
        });
    }

    // Method to check farm quantity via API
    private void checkFarmQty() {
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_MOBILE, "");
        String registeredPassword = shp.getString(KEY_PASSWORD, "");

        LoginRequestModel loginRequestModel = new LoginRequestModel(registeredUserNumber, registeredPassword);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";

        Call<LoginResponseModel> call = RetrofitInstance.getInstance().getMyApi().login(loginRequestModel);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponseModel farmResponse = response.body();
                    int farmQty = farmResponse.getFarmQty(); // Assume this method exists

                    if (farmQty > 0) {
                        // If farm quantity is greater than 0, go to MainActivity
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        // If farm quantity is 0, go to FarmActivity
                        startActivity(new Intent(LoginActivity.this, FarmAddActivity.class));
                    }
                    finish(); // Close the LoginActivity
                } else {
                    // Handle unsuccessful farm quantity check
                    Toast.makeText(LoginActivity.this, "Failed to check farm quantity", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                // Handle API failure
                Toast.makeText(LoginActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}