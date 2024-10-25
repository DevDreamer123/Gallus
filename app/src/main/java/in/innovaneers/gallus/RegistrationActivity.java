package in.innovaneers.gallus;

import static java.security.AccessController.getContext;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
    private ImageView ivShowHidePasswordRegi;
    private boolean isPasswordVisible = false;
    EditText user_name_regi,mobile_regi,email_regi,
            address_regi,area_regi,city_regi,date_regi,password_regi;
    ProgressBar progressBarRegi;
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
        ivShowHidePasswordRegi = findViewById(R.id.ivShowHidePasswordRegi);


        ivShowHidePasswordRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide the password
                    password_regi.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivShowHidePasswordRegi.setImageResource(R.drawable.baseline_visibility_off_24); // Change icon to visibility_off
                } else {
                    // Show the password
                    password_regi.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivShowHidePasswordRegi.setImageResource(R.drawable.baseline_visibility_24); // Change icon to visibility
                }
                isPasswordVisible = !isPasswordVisible;

                // Move cursor to the end of the text
                password_regi.setSelection(password_regi.getText().length());
            }
        });
        progressBarRegi = findViewById(R.id.progressBarRegi);
        user_name_regi = findViewById(R.id.user_name_regi);
        mobile_regi = findViewById(R.id.mobile_regi);
       // email_regi = findViewById(R.id.email_regi);
        //address_regi = findViewById(R.id.address_regi);
        //area_regi = findViewById(R.id.area_regi);
        city_regi = findViewById(R.id.city_regi);
        //date_regi = findViewById(R.id.date_regi);
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
        String currentDate = getCurrentDate();
        sign_up_btn = findViewById(R.id.sign_up_btn);
        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show a loading indicator to inform the user
                progressBarRegi.setVisibility(View.VISIBLE);  // Progress bar to show process is running

                if (TextUtils.isEmpty(user_name_regi.getText().toString()) ||
                        TextUtils.isEmpty(city_regi.getText().toString()) ||
                        TextUtils.isEmpty(mobile_regi.getText().toString()) ||
                        TextUtils.isEmpty(password_regi.getText().toString())) {
                    Toast.makeText(RegistrationActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                    return; // Ensure no further action occurs when details are missing
                }else {

                   // String date = date_regi.getText().toString().trim();

                    RegistrationRequestModel registerModel = new RegistrationRequestModel();
                    registerModel.setName(user_name_regi.getText().toString());
                   // registerModel.setEmail(email_regi.getText().toString());
                    registerModel.setMobile(mobile_regi.getText().toString());
                   // registerModel.setAddress(address_regi.getText().toString());
                    //registerModel.setArea(area_regi.getText().toString());
                    registerModel.setCity(city_regi.getText().toString());
                    registerModel.setRegistrationDate(currentDate);
                    registerModel.setPassword(password_regi.getText().toString());

                    RetrofitInstance.BASEURL = "http://api.gallus.in/";
                    try {
                        Call<RegistrationResponseModel> call = RetrofitInstance.getInstance().getMyApi().registration(registerModel);
                        call.enqueue(new Callback<RegistrationResponseModel>() {
                            @Override
                            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                                progressBarRegi.setVisibility(View.GONE);
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
       /* date_regi.setInputType(InputType.TYPE_NULL);
        date_regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });*/
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
   /* private void showDatePickerDialog() {
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
    }*/
    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Date format
        return dateFormat.format(calendar.getTime());
    }
}