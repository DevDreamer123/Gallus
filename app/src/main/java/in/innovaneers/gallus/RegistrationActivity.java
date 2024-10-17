package in.innovaneers.gallus;

import static java.security.AccessController.getContext;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

                if (TextUtils.isEmpty(user_name_regi.getText().toString()) ||
                        TextUtils.isEmpty(email_regi.getText().toString()) ||
                        TextUtils.isEmpty(mobile_regi.getText().toString()) ||
                        TextUtils.isEmpty(password_regi.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                    return; // Ensure no further action occurs when details are missing
                }else {

                    String date = date_regi.getText().toString().trim();

                    RegistrationRequestModel registerModel = new RegistrationRequestModel();
                    registerModel.setName(user_name_regi.getText().toString());
                    registerModel.setEmail(email_regi.getText().toString());
                    registerModel.setMobile(mobile_regi.getText().toString());
                    registerModel.setAddress(address_regi.getText().toString());
                    registerModel.setArea(area_regi.getText().toString());
                    registerModel.setCity(city_regi.getText().toString());
                    registerModel.setRegistrationDate(date);
                    registerModel.setPassword(password_regi.getText().toString());

                    RetrofitInstance.BASEURL = "http://api.gallus.in/";
                    try {
                        Call<RegistrationResponseModel> call = RetrofitInstance.getInstance().getMyApi().registration(registerModel);
                        call.enqueue(new Callback<RegistrationResponseModel>() {
                            @Override
                            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    Toast.makeText(RegistrationActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
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
        date_regi.setInputType(InputType.TYPE_NULL);
        date_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
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
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Update TextInputEditText with selected date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        date_regi.setText(dateFormat.format(calendar.getTime()));
                        Toast.makeText(RegistrationActivity.this, date_regi.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
}