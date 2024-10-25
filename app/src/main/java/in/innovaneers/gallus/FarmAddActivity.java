package in.innovaneers.gallus;

import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.innovaneers.gallus.Utils.HomeFragment;
import in.innovaneers.gallus.model.BatchRequestModel;
import in.innovaneers.gallus.model.FarmResponseModel;
import in.innovaneers.gallus.model.FarmerIdModel;
import in.innovaneers.gallus.model.FarmsModel;
import in.innovaneers.gallus.model.RegistrationRequestModel;
import in.innovaneers.gallus.model.RegistrationResponseModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import in.innovaneers.gallus.model.SharedPrefHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmAddActivity extends AppCompatActivity {


    EditText name_addFarm,address_addFarm,area_addFarm,city_addFarm,state_addFarm,mobile_addFarm,chicks_date_addFarm
            ,chicks_body_weight_addFarm,chicks_rate_addFarm,chicks__free_addFarm,pincode_addFarm,chicks_addFarm,size_addFarm;
    Button saveFarmButton;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    ProgressBar  progressBar;
    String farmApiId;
    String farmerId;
    String responseStatus;
    int flag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farm_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        shp = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
         farmerId =shp.getString(KEY_FARMER_ID,"");



        progressBar = findViewById(R.id.progressBar);
        name_addFarm = findViewById(R.id.name_addFarm);
        address_addFarm = findViewById(R.id.address_addFarm);
        mobile_addFarm = findViewById(R.id.mobile_addFarm);
        area_addFarm = findViewById(R.id.area_addFarm);
        city_addFarm = findViewById(R.id.city_addFarm);
        state_addFarm = findViewById(R.id.state_addFarm);
        pincode_addFarm = findViewById(R.id.pincode_addFarm);
        chicks_addFarm = findViewById(R.id.chicks_addFarm);
        size_addFarm = findViewById(R.id.size_addFarm);
        chicks__free_addFarm = findViewById(R.id.chicks__free_addFarm);
        chicks_rate_addFarm = findViewById(R.id.chicks_rate_addFarm);
        chicks_body_weight_addFarm = findViewById(R.id.chicks_body_weight_addFarm);
        chicks_date_addFarm = findViewById(R.id.chicks_date_addFarm);
        saveFarmButton = findViewById(R.id.saveFarmButton);
        chicks_date_addFarm.setInputType(InputType.TYPE_NULL);
        chicks_date_addFarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });





        saveFarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveFarmButton.setEnabled(false);

                // Show a loading indicator to inform the user
                progressBar.setVisibility(View.VISIBLE);  // Progress bar to show process is running

                if (TextUtils.isEmpty(name_addFarm.getText().toString())
                        || TextUtils.isEmpty(chicks_addFarm.getText().toString())
                        || TextUtils.isEmpty(address_addFarm.getText().toString())
                        || TextUtils.isEmpty(city_addFarm.getText().toString())
                        || TextUtils.isEmpty(chicks_rate_addFarm.getText().toString())  // RateAmount check
                        || TextUtils.isEmpty(chicks_body_weight_addFarm.getText().toString())  // BodyWeight check
                        || TextUtils.isEmpty(chicks_date_addFarm.getText().toString())) {  // Date check

                    Toast.makeText(FarmAddActivity.this, "Please Enter All Mandatory Details", Toast.LENGTH_SHORT).show();
                } else  {


                    submitFarmDetails();


                }





            }


        });






    }
    private void submitFarmDetails() {
        progressBar.setVisibility(View.VISIBLE);

        // Get values from EditTexts
        String name = name_addFarm.getText().toString();
        String address = address_addFarm.getText().toString();
        String area = area_addFarm.getText().toString();
        String city = city_addFarm.getText().toString();
        String state = state_addFarm.getText().toString();
        String pincode = pincode_addFarm.getText().toString();
        int chicks = Integer.parseInt(chicks_addFarm.getText().toString());
        String size = size_addFarm.getText().toString();
        int freeChicks = Integer.parseInt(chicks__free_addFarm.getText().toString());
        double purchaseRate = Double.parseDouble(chicks_rate_addFarm.getText().toString());
        int bodyWeight = Integer.parseInt((chicks_body_weight_addFarm.getText().toString()));
        String date = chicks_date_addFarm.getText().toString();

        RetrofitInstance.BASEURL = "http://api.gallus.in/";
        FarmsModel farmsModel = new FarmsModel(farmerId, name, address, area, city, state, pincode, chicks, size,freeChicks, bodyWeight, date, purchaseRate);
        try {
            Call<FarmResponseModel> call = RetrofitInstance.getInstance().getMyApi().farmAdd(farmsModel);
            call.enqueue(new Callback<FarmResponseModel>() {
                @Override
                public void onResponse(Call<FarmResponseModel> call, Response<FarmResponseModel> response) {
                    progressBar.setVisibility(View.GONE);
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(FarmAddActivity.this, "Successfully added farm", Toast.LENGTH_SHORT).show();
                        farmApiId = response.body().getFarmID();
                        responseStatus = response.body().getStatus();
                            Toast.makeText(FarmAddActivity.this, "Batch Added Successfully", Toast.LENGTH_SHORT).show();
                           Intent i = new Intent(FarmAddActivity.this,MainActivity.class);
                           startActivity(i);
                            /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            transaction.replace(R.id.container, new AccountFragment());
                            transaction.commit();*/
                            addBatch(date);

                        //saveFarmButton.setEnabled(false);
                        //batchAddAPI(farmApiId,chicks,freeChicks,bodyWeight,purchaseRate,date);

                        saveFarmButton.setEnabled(true);

                    } else {
                        // Error handling
                                  /*  String errorMessage = "";
                                    if (response.errorBody() != null) {
                                        try {
                                            String errorBody = response.errorBody().string();
                                            if (errorBody.contains("<html")) {
                                                errorMessage = "Server error occurred. Please try again later.";
                                            } else {
                                                errorMessage = errorBody;
                                            }
                                        } catch (Exception e) {
                                            errorMessage = "Error reading errorBody: " + e.getMessage();
                                        }
                                    }*/
                        Toast.makeText(FarmAddActivity.this, "Failed to add farm: " , Toast.LENGTH_SHORT).show();
                        Log.e("API Error", "Unsuccessful response: " );
                    }
                    Log.d("URl", RetrofitInstance.BASEURL + "Farms/Add?" +
                            "FarmerID=" + farmerId +
                            "&Name=" + name_addFarm.getText().toString() +
                            "&Address=" + address_addFarm.getText().toString() +
                            "&Area=" + area_addFarm.getText().toString() +
                            "&City=" + city_addFarm.getText().toString() +
                            "&State=" + state_addFarm.getText().toString() +
                            "&Pincode=" + pincode_addFarm.getText().toString() +
                            "&Chicks=" + chicks_addFarm.getText().toString() +
                            "&FreeChicks=" + chicks__free_addFarm.getText().toString() +
                            "&BodyWeight=" + chicks_body_weight_addFarm.getText().toString() +
                            "&Date=" + chicks_date_addFarm.getText().toString() +
                            "&PurchaseRate=" + chicks_rate_addFarm.getText().toString() +
                            "&Size=" + size_addFarm.getText().toString());



                }


                @Override
                public void onFailure(Call<FarmResponseModel> call, Throwable t) {
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(FarmAddActivity.this, "Failed to connect to the server: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("API Error", t.getMessage(), t);
                }
            });




        } catch (Exception e) {
            Toast.makeText(FarmAddActivity.this, "An unexpected error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("API Error", e.getMessage(), e);
        }
        getCurrentFarmFromAPI();

    }
    // Method to fetch farm from API
    private void getCurrentFarmFromAPI() {
        RetrofitInstance.BASEURL = "http://api.gallus.in/";
        FarmerIdModel farmerIdModel = new FarmerIdModel(farmerId);

        Call<List<FarmsModel>> call = RetrofitInstance.getInstance().getMyApi().farmList(farmerIdModel);
        call.enqueue(new Callback<List<FarmsModel>>() {
            @Override
            public void onResponse(Call<List<FarmsModel>> call, Response<List<FarmsModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<FarmsModel> farmsModels = response.body();
                    if (!farmsModels.isEmpty()) {
                        FarmsModel latestFarm = farmsModels.get(farmsModels.size() - 1);

                        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(FarmAddActivity.this);
                        sharedPrefHelper.saveSelectedFarm(latestFarm.getFarmID(), latestFarm.getName());

                    } else {
                        Toast.makeText(FarmAddActivity.this, "No farms available", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(FarmAddActivity.this, "Failed to load farms", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FarmsModel>> call, Throwable t) {
                Toast.makeText(FarmAddActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void addBatch(String batchName){
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putString("batchDate",getCurrentDate());
        editor.apply();

    }
    private String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }


    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(FarmAddActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Update TextInputEditText with selected date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        chicks_date_addFarm.setText(dateFormat.format(calendar.getTime()));
                        Toast.makeText(FarmAddActivity.this, "Date"+chicks_date_addFarm.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }



}