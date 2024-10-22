package in.innovaneers.gallus;

import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;
import static in.innovaneers.gallus.LoginActivity.KEY_MOBILE;
import static in.innovaneers.gallus.LoginActivity.KEY_PASSWORD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import in.innovaneers.gallus.model.FarmerIdModel;
import in.innovaneers.gallus.model.LoginRequestModel;
import in.innovaneers.gallus.model.LoginResponseModel;
import in.innovaneers.gallus.model.RegistrationResponseModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    public static String farmerID;
    public static String userNameId;
    public static String passwordId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
      /*  new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Check if the user is logged in
                if (isUserLoggedIn() ) {
                    startActivity(new Intent(SplashActivity.this, PlanActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
                finish();

            }
        },5000);*/
        loginAndCheckPlan();

    }

    private void loginAndCheckPlan() {
        // Check if the user is logged in
        if (isUserLoggedIn()) {
            // Step 1: Check Plan Validity via API
            checkPlanValidity();
        } else {
            // Redirect to LoginActivity if the user is not logged in
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }

    // Method to check plan validity via API
    private void checkPlanValidity() {
        FarmerIdModel farmerIdModel = new FarmerIdModel(farmerID);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";

        Call<RegistrationResponseModel> call = RetrofitInstance.getInstance().getMyApi().createValidPlan(farmerIdModel);
        call.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegistrationResponseModel planValidity = response.body();

                    // Step 2: Check if the plan is valid
                    if ("Success".equals(planValidity.getStatus())) {
                        // Plan is valid, check the farm quantity
                        checkFarmQty();
                    } else {
                        // Plan is not valid, navigate to PlanActivity
                        startActivity(new Intent(SplashActivity.this, PlanActivity.class));
                        finish();
                    }
                } else {
                    // Failed to check plan validity, navigate to PlanActivity
                    Toast.makeText(SplashActivity.this, "Failed to check plan validity", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashActivity.this, PlanActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                // Handle API failure
                Toast.makeText(SplashActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SplashActivity.this, PlanActivity.class));
                finish();
            }
        });
    }

    // Method to check farm quantity via API
    private void checkFarmQty() {
        // Create login request model with user credentials
        LoginRequestModel loginRequestModel = new LoginRequestModel(userNameId, passwordId);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";

        Call<LoginResponseModel> call = RetrofitInstance.getInstance().getMyApi().login(loginRequestModel);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponseModel loginResponse = response.body();

                    // Step 3: Get Current Plan and FarmQty from the response
                    String currentPlan = loginResponse.getCurrentPlan();
                    int farmQty = loginResponse.getFarmQty();

                    new Handler().postDelayed(() -> {
                        if (currentPlan != null && !currentPlan.isEmpty() && farmQty > 0) {
                            // If plan is valid and farm quantity is greater than 0, go to MainActivity
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        } else if (farmQty == 0) {
                            // If farm quantity is 0, go to FarmActivity
                            startActivity(new Intent(SplashActivity.this, FarmAddActivity.class));
                        }
                        finish(); // Close the SplashActivity
                    }, 5000); // 5-second delay
                } else {
                    // If login API response fails, show an error and move to LoginActivity
                    Toast.makeText(SplashActivity.this, "Login failed, please check your credentials", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                // Handle API failure
                Toast.makeText(SplashActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    // Check if the user is logged in
    private boolean isUserLoggedIn() {
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_MOBILE, "");
        String registeredPassword = shp.getString(KEY_PASSWORD, "");
        userNameId = registeredUserNumber;
        passwordId = registeredPassword;
        farmerID = shp.getString(KEY_FARMER_ID, "");

        return !registeredUserNumber.isEmpty() && !registeredPassword.isEmpty();





















  /*  private boolean isUserLoggedIn() {
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_MOBILE,"");
        String registeredPassword = shp.getString(KEY_PASSWORD,"");
        userNameId = registeredUserNumber;
        passwordId = registeredPassword;
        farmerID = shp.getString(KEY_FARMER_ID,"");

        return !registeredUserNumber.isEmpty() && !registeredPassword.isEmpty();
    }
    // Method to check plan validity via API
    private void checkPlanValidity() {
       // String farmerID = "3cff7f72-00c5-4bea-9cfc-23c7fb73431b"; // Replace this with your actual FarmerID
        FarmerIdModel farmerIdModel = new FarmerIdModel(farmerID);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";

        Call<RegistrationResponseModel> call = RetrofitInstance.getInstance().getMyApi().createValidPlan(farmerIdModel);
        call.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Plan validity check
                    RegistrationResponseModel planValidity = response.body();
                    if (planValidity.equals("Success")) {
                        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
                        String registeredUserNumber = shp.getString(KEY_MOBILE,"");
                        String registeredPassword = shp.getString(KEY_PASSWORD,"");
                        userNameId = registeredUserNumber;
                        passwordId = registeredPassword;
                        farmerID = shp.getString(KEY_FARMER_ID,"");
                        LoginRequestModel loginRequestModel = new LoginRequestModel(userNameId , passwordId);
                        RetrofitInstance.BASEURL = "http://api.gallus.in/";

                        // Create the API call
                        Call<LoginResponseModel> call = RetrofitInstance.getInstance().getMyApi().login(loginRequestModel);
                        call.enqueue(new Callback<LoginResponseModel>() {
                            @Override
                            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    LoginResponseModel loginResponse = response.body();

                                    // Get Current Plan and FarmQty from the response
                                    String currentPlan = loginResponse.getCurrentPlan();
                                    int farmQty = loginResponse.getFarmQty();

                                    // 5 seconds delay before moving to the next activity
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            // Check if the user is logged in and plan is active
                                            if (currentPlan != null && !currentPlan.isEmpty() && farmQty > 0) {
                                                // If plan is valid and farm quantity is greater than 0, go to MainActivity
                                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                            } else {
                                                // If plan is not valid or farm quantity is 0, go to PlanActivity
                                                startActivity(new Intent(SplashActivity.this, PlanActivity.class));
                                            }
                                            finish(); // Close the SplashActivity
                                        }
                                    }, 5000); // 5-second delay
                                } else {
                                    // If API response is not successful, show an error and move to LoginActivity
                                    Toast.makeText(SplashActivity.this, "Login failed, please check your credentials", Toast.LENGTH_SHORT).show();

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Redirect to LoginActivity after 5 seconds
                                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                            finish();
                                        }
                                    }, 5000); // 5-second delay
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                                // Handle API failure
                                Toast.makeText(SplashActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Redirect to LoginActivity after 5 seconds on failure
                                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                }, 5000); // 5-second delay
                            }
                        });
                    } else {
                        // Plan is expired or invalid, go to PlanActivity
                        Intent intent = new Intent(SplashActivity.this, PlanActivity.class);
                        startActivity(intent);
                        finish(); // Close splash activity
                    }
                } else {
                    Toast.makeText(SplashActivity.this, "Failed to check plan validity", Toast.LENGTH_SHORT).show();
                    // Fallback: You can choose to either show PlanActivity or retry
                    Intent intent = new Intent(SplashActivity.this, PlanActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                // Handle API failure, you can show PlanActivity or retry
                Intent intent = new Intent(SplashActivity.this, PlanActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Method to call login API and check plan status
    private void loginAndCheckPlan() {
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_MOBILE,"");
        String registeredPassword = shp.getString(KEY_PASSWORD,"");
        userNameId = registeredUserNumber;
        passwordId = registeredPassword;
        farmerID = shp.getString(KEY_FARMER_ID,"");
      LoginRequestModel loginRequestModel = new LoginRequestModel(userNameId , passwordId);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";

        // Create the API call
        Call<LoginResponseModel> call = RetrofitInstance.getInstance().getMyApi().login(loginRequestModel);
        call.enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponseModel loginResponse = response.body();

                    // Get Current Plan and FarmQty from the response
                    String currentPlan = loginResponse.getCurrentPlan();
                    int farmQty = loginResponse.getFarmQty();

                    // 5 seconds delay before moving to the next activity
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            // Check if the user is logged in and plan is active
                            if (currentPlan != null && !currentPlan.isEmpty() && farmQty > 0) {
                                // If plan is valid and farm quantity is greater than 0, go to MainActivity
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            } else {
                                // If plan is not valid or farm quantity is 0, go to PlanActivity
                                startActivity(new Intent(SplashActivity.this, PlanActivity.class));
                            }
                            finish(); // Close the SplashActivity
                        }
                    }, 5000); // 5-second delay
                } else {
                    // If API response is not successful, show an error and move to LoginActivity
                    Toast.makeText(SplashActivity.this, "Login failed, please check your credentials", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Redirect to LoginActivity after 5 seconds
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            finish();
                        }
                    }, 5000); // 5-second delay
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                // Handle API failure
                Toast.makeText(SplashActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Redirect to LoginActivity after 5 seconds on failure
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                }, 5000); // 5-second delay
            }
        });
    }*/
    }
}