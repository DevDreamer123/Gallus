package in.innovaneers.gallus;

import android.os.Bundle;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import in.innovaneers.gallus.model.FarmsModel;
import in.innovaneers.gallus.model.RecordRequestModel;
import in.innovaneers.gallus.model.RecordResponseModel;
import in.innovaneers.gallus.model.RegistrationResponseModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyRecordActivity extends AppCompatActivity {


    EditText editTextDate_record,editTextbrand_record,editTextGrainAmount_record,
            editTextfeedconstruction_record,editTextBirdWeight_record,editTextMortalityRate_record,
            editTextHealthStatus_record,editTextWaterConsumed_record;
    TextView day_of_record_from;

    Button buttonSubmit_record;

    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    private String currentBatchId;
    private String day_of_record;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_daily_record);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
         currentBatchId = shp.getString("currentBatchID","");
        Log.d("currentBatchId",currentBatchId);


        editTextbrand_record = findViewById(R.id.editTextbrand_record);
        editTextGrainAmount_record = findViewById(R.id.editTextGrainAmount_record);
        editTextfeedconstruction_record = findViewById(R.id.editTextfeedconstruction_record);
        editTextBirdWeight_record = findViewById(R.id.editTextBirdWeight_record);
        editTextMortalityRate_record = findViewById(R.id.editTextMortalityRate_record);
        editTextHealthStatus_record = findViewById(R.id.editTextHealthStatus_record);
        editTextWaterConsumed_record = findViewById(R.id.editTextWaterConsumed_record);
        editTextDate_record = findViewById(R.id.editTextDate_record);
        day_of_record_from = findViewById(R.id.day_of_record_from);
        showCurrentDay();
        buttonSubmit_record = findViewById(R.id.buttonSubmit_record);
        buttonSubmit_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editTextbrand_record.getText().toString()) && TextUtils.isEmpty(editTextBirdWeight_record.getText().toString()) && TextUtils.isEmpty(editTextfeedconstruction_record.getText().toString()) && TextUtils.isEmpty(editTextMortalityRate_record.getText().toString())){
                    Toast.makeText(DailyRecordActivity.this, "Please Enter All Details", Toast.LENGTH_SHORT).show();
                }else {


                   apiResponse();

                }



            }
        });
        editTextDate_record.setInputType(InputType.TYPE_NULL);
        editTextDate_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });


    }
    private void apiResponse(){
        RecordRequestModel recordRequestModel = new RecordRequestModel();
        recordRequestModel.setBatchID(currentBatchId);
        recordRequestModel.setAge(day_of_record);
        recordRequestModel.setFeedBrand(editTextbrand_record.getText().toString());
        recordRequestModel.setDate(editTextDate_record.getText().toString());
        recordRequestModel.setFeedConsumption(editTextGrainAmount_record.getText().toString());
        recordRequestModel.setMortalityCount(editTextfeedconstruction_record.getText().toString());
        recordRequestModel.setBodyWeight(editTextBirdWeight_record.getText().toString());
        recordRequestModel.setMortalityTotal(editTextMortalityRate_record.getText().toString());
        recordRequestModel.setMedicine(editTextHealthStatus_record.getText().toString());
        recordRequestModel.setWaterConsumption(editTextWaterConsumed_record.getText().toString());
        RetrofitInstance.BASEURL = " http://gallus.innovaneers.in/";
        try {
            Call<RecordResponseModel> call = RetrofitInstance.getInstance().getMyApi().AddRecord(recordRequestModel);
            call.enqueue(new Callback<RecordResponseModel>() {
                @Override
                public void onResponse(Call<RecordResponseModel> call, Response<RecordResponseModel> response) {
                    Intent intent = new Intent(DailyRecordActivity.this, MainActivity.class);
                    Toast.makeText(DailyRecordActivity.this,"Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    Log.d("Date",editTextDate_record.toString());

                    finish();

                    // Toast.makeText(RegistrationActivity.this, "SuccessFully", Toast.LENGTH_SHORT).show();


                }

                @Override
                public void onFailure(Call<RecordResponseModel> call, Throwable t) {
                    Toast.makeText(DailyRecordActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });

        } catch (Exception e) {
            Toast.makeText(DailyRecordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }




    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(DailyRecordActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.YEAR, year);



                        // Update TextInputEditText with selected date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        editTextDate_record.setText(dateFormat.format(calendar.getTime()));
                        Toast.makeText(DailyRecordActivity.this, "Date"+editTextDate_record, Toast.LENGTH_SHORT).show();
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }
    private void showCurrentDay() {
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String batchDateString = shp.getString("batchDate", null);

        if (batchDateString != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date batchDate = sdf.parse(batchDateString);
                Date currentDate = new Date();

                // Calculate the difference in days
                long differenceInMillis = currentDate.getTime() - batchDate.getTime();
                long daysDifference = TimeUnit.DAYS.convert(differenceInMillis, TimeUnit.MILLISECONDS);

                // Show the day as "1st day", "2nd day", etc.
                String dayString = (daysDifference + 1) + (daysDifference == 0 ? "st" : "th") + " day";
                day_of_record = String.valueOf((daysDifference + 1) + (daysDifference));
                Toast.makeText(this, day_of_record, Toast.LENGTH_SHORT).show();
                day_of_record_from.setText(dayString);
                Toast.makeText(this, dayString, Toast.LENGTH_SHORT).show();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}