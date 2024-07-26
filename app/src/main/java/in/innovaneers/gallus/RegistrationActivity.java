package in.innovaneers.gallus;

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

import in.innovaneers.gallus.model.RegistrationRequestModel;
import in.innovaneers.gallus.model.RegistrationResponseModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
Button sign_up_btn;
TextView sign_in_text;

EditText user_name_regi,mobile_regi,email_regi,address_regi,area_regi,city_regi,date_regi,password_regi;
    boolean isAllFieldsChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        user_name_regi = findViewById(R.id.user_name_regi);
        mobile_regi = findViewById(R.id.mobile_regi);
        email_regi = findViewById(R.id.email_regi);
        address_regi = findViewById(R.id.address_regi);
        area_regi = findViewById(R.id.area_regi);
        city_regi = findViewById(R.id.city_regi);
        date_regi = findViewById(R.id.date_regi);
        password_regi = findViewById(R.id.password_regi);


        sign_in_text = findViewById(R.id.sign_in_text);
        sign_in_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        sign_up_btn = findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(user_name_regi.getText().toString()) && TextUtils.isEmpty(email_regi.getText().toString()) && TextUtils.isEmpty(mobile_regi.getText().toString()) && TextUtils.isEmpty(password_regi.getText().toString())){
                    Toast.makeText(RegistrationActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }else {


                    RegistrationRequestModel registerModel = new RegistrationRequestModel();
                    registerModel.setName(user_name_regi.getText().toString());
                    registerModel.setEmail(email_regi.getText().toString());
                    registerModel.setMobile(mobile_regi.getText().toString());
                    registerModel.setAddress(address_regi.getText().toString());
                    registerModel.setArea(area_regi.getText().toString());
                    registerModel.setCity(city_regi.getText().toString());
                    registerModel.setRegistrationDate(date_regi.getText().toString());
                    registerModel.setPassword(password_regi.getText().toString());

                    RetrofitInstance.BASEURL = " http://gallus.innovaneers.in/";
                    try {
                        Call<RegistrationResponseModel> call = RetrofitInstance.getInstance().getMyApi().registration(registerModel);
                        call.enqueue(new Callback<RegistrationResponseModel>() {
                            @Override
                            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                Toast.makeText(RegistrationActivity.this,response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                                finish();

                               // Toast.makeText(RegistrationActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

                    } catch (Exception e) {
                        Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
            private boolean CheckAllFields() {
                //String MobilePattern = "[0-9]{10}";
                //String email1 = email.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (user_name_regi.length() == 0) {
                    user_name_regi.setError("This field is required");
                    return false;
                }


                if(!email_regi.getText().toString().matches(emailPattern)) {

                    Toast.makeText(getApplicationContext(),"Please Enter Valid Email Address",Toast.LENGTH_SHORT).show();
                    return false;
                }

              //  if(mobile_regi.getText().toString().matches(MobilePattern)) {

                //    Toast.makeText(getApplicationContext(), "phone number is valid", Toast.LENGTH_SHORT).show();
                //    return true;
             //   }
                else if (password_regi.length() < 8) {
                    password_regi.setError("Password must be minimum 8 characters");
                    return false;
                }

                // after all validation return true.
                return true;

    }
}