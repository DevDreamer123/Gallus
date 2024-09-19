package in.innovaneers.gallus;

import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import in.innovaneers.gallus.model.FarmsModel;
import in.innovaneers.gallus.model.RegistrationRequestModel;
import in.innovaneers.gallus.model.RegistrationResponseModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FarmAddActivity extends AppCompatActivity {


    EditText name_addFarm,address_addFarm,area_addFarm,city_addFarm,state_addFarm,mobile_addFarm,pincode_addFarm,chicks_addFarm,size_addFarm;
    Button saveFarmButton;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";


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
        saveFarmButton = findViewById(R.id.saveFarmButton);


        saveFarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name_addFarm.getText().toString()) && TextUtils.isEmpty(chicks_addFarm.getText().toString()) && TextUtils.isEmpty(address_addFarm.getText().toString()) && TextUtils.isEmpty(city_addFarm.getText().toString())){
                    Toast.makeText(FarmAddActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }else {


                    FarmsModel farmsModel = new FarmsModel();
                    farmsModel.setFarmerID(farmerId);
                    farmsModel.setName(name_addFarm.getText().toString());
                    farmsModel.setAddress(address_addFarm.getText().toString());
                    farmsModel.setArea(area_addFarm.getText().toString());
                    farmsModel.setCity(city_addFarm.getText().toString());
                    farmsModel.setState(state_addFarm.getText().toString());
                    farmsModel.setPincode(pincode_addFarm.getText().toString());
                    farmsModel.setSize(size_addFarm.getText().toString());
                    farmsModel.setChicks(chicks_addFarm.getText().toString());

                    RetrofitInstance.BASEURL = " http://gallus.innovaneers.in/";
                    try {
                        Call<RegistrationResponseModel> call = RetrofitInstance.getInstance().getMyApi().farmAdd(farmsModel);
                        call.enqueue(new Callback<RegistrationResponseModel>() {
                            @Override
                            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                                Intent intent = new Intent(FarmAddActivity.this, MainActivity.class);
                                Toast.makeText(FarmAddActivity.this,"Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                                finish();

                                // Toast.makeText(RegistrationActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                                Toast.makeText(FarmAddActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

                    } catch (Exception e) {
                        Toast.makeText(FarmAddActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });









    }
}