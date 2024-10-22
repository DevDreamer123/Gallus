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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import in.innovaneers.gallus.model.BatchRequestModel;
import in.innovaneers.gallus.model.FarmResponseModel;
import in.innovaneers.gallus.model.FarmsModel;
import in.innovaneers.gallus.model.RegistrationRequestModel;
import in.innovaneers.gallus.model.RegistrationResponseModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmAddActivity extends AppCompatActivity {


    EditText name_addFarm,address_addFarm,area_addFarm,city_addFarm,state_addFarm,mobile_addFarm,chicks_date_addFarm
            ,chicks_body_weight_addFarm,chicks_rate_addFarm,chicks__free_addFarm,pincode_addFarm,chicks_addFarm,size_addFarm;
    Button saveFarmButton;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";

    TextInputEditText chicks_no_edit_popup,purchase_edit_popup;
    TextInputEditText free_chicks_no_edit_popup,editTextDate,body_weight_text_edit_popup;
    private static String cuFarmID;


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
        String farmerId =shp.getString(KEY_FARMER_ID,"");




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
                if (TextUtils.isEmpty(name_addFarm.getText().toString())
                        || TextUtils.isEmpty(chicks_addFarm.getText().toString())
                        || TextUtils.isEmpty(address_addFarm.getText().toString())
                        || TextUtils.isEmpty(city_addFarm.getText().toString())
                        || TextUtils.isEmpty(chicks_rate_addFarm.getText().toString())  // RateAmount check
                        || TextUtils.isEmpty(chicks_body_weight_addFarm.getText().toString())  // BodyWeight check
                        || TextUtils.isEmpty(chicks_date_addFarm.getText().toString())) {  // Date check

                    Toast.makeText(FarmAddActivity.this, "Please Enter All Mandatory Details", Toast.LENGTH_SHORT).show();
                } else  {

                     FarmsModel farmsModel = new FarmsModel();
                    farmsModel.setFarmerID(farmerId);
                    farmsModel.setName(name_addFarm.getText().toString());
                    farmsModel.setAddress(address_addFarm.getText().toString());
                    farmsModel.setArea(area_addFarm.getText().toString());
                    farmsModel.setCity(city_addFarm.getText().toString());
                    farmsModel.setState(state_addFarm.getText().toString());
                    farmsModel.setPincode(pincode_addFarm.getText().toString());
                    farmsModel.setFreeChicks(chicks__free_addFarm.getText().toString());
                    farmsModel.setPurchaseAmount(chicks_rate_addFarm.getText().toString());  // Mandatory field
                    farmsModel.setBodyWeight(chicks_body_weight_addFarm.getText().toString());  // Mandatory field
                    farmsModel.setDate(chicks_date_addFarm.getText().toString());  // Mandatory field
                    farmsModel.setSize(size_addFarm.getText().toString());
                    farmsModel.setChicks(chicks_addFarm.getText().toString());

                    RetrofitInstance.BASEURL = "http://api.gallus.in/";
                    try {
                        Call<FarmResponseModel> call = RetrofitInstance.getInstance().getMyApi().farmAdd(farmsModel);
                        call.enqueue(new Callback<FarmResponseModel>() {
                            @Override
                            public void onResponse(Call<FarmResponseModel> call, Response<FarmResponseModel> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    Toast.makeText(FarmAddActivity.this, "Successfully added farm", Toast.LENGTH_SHORT).show();


                                    String farmApiId = response.body().getFarmID();
                                    batchAdd(farmApiId, chicks_addFarm.getText().toString(),
                                            chicks__free_addFarm.getText().toString(),
                                            chicks_body_weight_addFarm.getText().toString(),
                                            chicks_rate_addFarm.getText().toString(),
                                            chicks_date_addFarm.getText().toString());


                                } else {
                                    // Error handling
                                    String errorMessage = "";
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
                                    }
                                    Toast.makeText(FarmAddActivity.this, "Failed to add farm: " + errorMessage, Toast.LENGTH_SHORT).show();
                                    Log.e("API Error", "Unsuccessful response: " + errorMessage);
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

                                //  Intent intent = new Intent(FarmAddActivity.this, MainActivity.class);
                               // startActivity(intent);
                              //  finish();
                            }

                            @Override
                            public void onFailure(Call<FarmResponseModel> call, Throwable t) {
                                Toast.makeText(FarmAddActivity.this, "Failed to connect to the server: " + t.getMessage(), Toast.LENGTH_LONG).show();
                                Log.e("API Error", t.getMessage(), t);
                            }
                        });

                    } catch (Exception e) {
                        Toast.makeText(FarmAddActivity.this, "An unexpected error occurred: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("API Error", e.getMessage(), e);
                    }
                }



            }
        });
       /* BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String farmId = intent.getStringExtra("farm_id");
                cuFarmID = farmId;

            }
        };

        Log.d("CuFarmIdAdd",cuFarmID);

        IntentFilter filter = new IntentFilter("farm_id_action");
        registerReceiver(receiver, filter);*/









    }
    private void batchAdd(String farmId, String chicks, String freeChicks, String bodyWeight, String purchaseRate, String date) {
        BatchRequestModel model = new BatchRequestModel(farmId, chicks, freeChicks, bodyWeight, date, purchaseRate);

        RetrofitInstance.BASEURL = "http://api.gallus.in/";
        Call<RegistrationResponseModel> call = RetrofitInstance.getInstance().getMyApi().addBatch(model);
        call.enqueue(new Callback<RegistrationResponseModel>() {
            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(FarmAddActivity.this, "Batch Added Successfully", Toast.LENGTH_SHORT).show();
                    addBatch(date);
                    Intent intent = new Intent(FarmAddActivity.this, MainActivity.class);
                    SharedPreferences.Editor editor = shp.edit();
                    editor.putString("selectedFarmId", farmId);
                    editor.apply();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(FarmAddActivity.this, "Failed to add batch", Toast.LENGTH_SHORT).show();
                }
                Log.d("URl", RetrofitInstance.BASEURL + "Batches/Add?" +
                        "FarmID=" + farmId +
                        "&Chicks=" + chicks +
                        "&FreeChicks=" + freeChicks +
                        "&BodyWeight=" + bodyWeight +
                        "&Date=" + date +
                        "&PurchaseRate=" + purchaseRate);
            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                Toast.makeText(FarmAddActivity.this, "Failed to connect to the server", Toast.LENGTH_SHORT).show();
            }
        });


                // Reset Spinner
                //spinner.setSelection(0);
         /*   }
        });
        editTextDate.setInputType(InputType.TYPE_NULL);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Close button click listener
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FarmAddActivity.this,MainActivity.class);
                startActivity(i);

                dialog.dismiss();
            }
        });*/







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


    private  void handleError(String errorMessage){
        Log.e("API Error", errorMessage);
        Toast.makeText(FarmAddActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show();
    }

}