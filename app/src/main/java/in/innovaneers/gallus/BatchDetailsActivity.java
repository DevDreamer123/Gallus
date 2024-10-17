package in.innovaneers.gallus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.innovaneers.gallus.model.BatchDetailsAdapter;
import in.innovaneers.gallus.model.BatchIdModel;
import in.innovaneers.gallus.model.DailyRecordHistoryModel;
import in.innovaneers.gallus.model.Method;
import in.innovaneers.gallus.model.RecordRequestModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatchDetailsActivity extends AppCompatActivity {
    private RecyclerView batchListDetailRecyclerView;
    private BatchIdModel batchIdModel;
    SharedPreferences shp;
    TextView current_batch_record_id;
    public static final String SHARED_PREF_NAME = "Gallus";
    private static final int STORAGE_PERMISSION_CODE = 101;
    private static String CurrentBatchId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_batch_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        current_batch_record_id = findViewById(R.id.current_batch_record_id);


        // Retrieve and Display the Batch ID
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String currentBatchId = shp.getString("currentBatchID", "");
        Log.d("StoredBatchId", currentBatchId);

        CurrentBatchId = currentBatchId;
        if (CurrentBatchId == null || CurrentBatchId.isEmpty()) {
            current_batch_record_id.setText(R.string.no_batch_id_current);
        } else {
            current_batch_record_id.setText(CurrentBatchId);
        }


       /* // Request storage permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        } else {
            fetchDataFromApi();
        }*/
        fetchDataFromApi();
    }

    private void fetchDataFromApi() {
        // recycler_records_list = findViewById(R.id.recycler_records_list);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";
        BatchIdModel farmIdModel = new BatchIdModel(CurrentBatchId);
        try {
            Call<List<DailyRecordHistoryModel>> call = RetrofitInstance.getInstance().getMyApi().recordsHistoryRecord(farmIdModel);
            call.enqueue(new Callback<List<DailyRecordHistoryModel>>() {
                @Override
                public void onResponse(Call<List<DailyRecordHistoryModel>> call, Response<List<DailyRecordHistoryModel>> response) {
                    Toast.makeText(BatchDetailsActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    Toast.makeText(BatchDetailsActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                    if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                        //createExcelFile(response.body());
                        List<DailyRecordHistoryModel> dailyRecordHistoryModels = response.body();
                        if (dailyRecordHistoryModels != null) {
                            populateTable(dailyRecordHistoryModels);
                        }

                    } else {
                        Toast.makeText(BatchDetailsActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                    }

                }


                @Override
                public void onFailure(Call<List<DailyRecordHistoryModel>> call, Throwable t) {
                    Toast.makeText(BatchDetailsActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("error", t.getMessage());

                    t.toString();
                }
            });

        } catch (Exception e) {
            Toast.makeText(BatchDetailsActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.d("error1", e.getMessage());
            e.getMessage();
        }
    }

    // Function to populate TableLayout
    private void populateTable(List<DailyRecordHistoryModel> records) {
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (DailyRecordHistoryModel record : records) {
            TableRow tableRow = new TableRow(this);

            // Date Column
            TextView dateView = new TextView(this);
            dateView.setText(record.getDate() != null ? Method.UnixToDate(record.getDate()) : "N/A");
            tableRow.addView(dateView);

            // Age Column
            TextView ageView = new TextView(this);

            ageView.setText(String.valueOf(record.getAge()));
            ageView.setPadding(50,0,0,0);
            ageView.setText(record.getAge() != null ? String.valueOf(record.getAge()) : "N/A");
            tableRow.addView(ageView);

            // Housed Column
            TextView housedView = new TextView(this);

            housedView.setText(String.valueOf(record.getHoused()));
            housedView.setPadding(50,0,0,0);

            housedView.setText(record.getHoused() != null ? String.valueOf(record.getHoused()) : "N/A");

            tableRow.addView(housedView);

            // Mortality (Daily/Total/Cum%)
            //LinearLayout mortalityLayout = new LinearLayout(this);
            //.setOrientation(LinearLayout.HORIZONTAL);

            // Daily Mortality
            TextView dailyMortalityCountView = new TextView(this);
            dailyMortalityCountView.setText(record.getMortalityCount() != null ? String.valueOf(record.getMortalityCount()) : "N/A");
            dailyMortalityCountView.setPadding(150,0,0,0);
            tableRow.addView(dailyMortalityCountView);

            // Total Mortality
            TextView totalMortalityView = new TextView(this);
            totalMortalityView.setText(record.getMortalityCount() != null ? String.valueOf(record.getMortalityCount()) : "N/A");
            totalMortalityView.setPadding(150,0,0,0);
            tableRow.addView(totalMortalityView);

            // Cumulative Mortality Percentage
            TextView PerMortalityView = new TextView(this);
            PerMortalityView.setText(record.getMortalityCount() != null ? String.valueOf(record.getMortalityCount()) : "N/A");
            PerMortalityView.setPadding(200,0,0,0);
            tableRow.addView(PerMortalityView);

            //tableRow.addView(mortalityLayout);

            // Mortality (Standard and Actual)
            //LinearLayout mortalityStdActualLayout = new LinearLayout(this);
            //mortalityStdActualLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Mortality
            TextView feedBrandView = new TextView(this);
            feedBrandView.setText(record.getFeedBrand() != null ? String.valueOf(record.getFeedBrand()) : "N/A");
            feedBrandView.setPadding(100,0,0,0);
            tableRow.addView(feedBrandView);

            // Actual Mortality
           // TextView actualMortalityView = new TextView(this);
           // actualMortalityView.setText(record.getMortalityTotal() != null ? String.valueOf(record.getMortalityTotal()) : "N/A");
            //tableRow.addView(actualMortalityView);

          //  tableRow.addView(actualMortalityView);

            // Feed Intake (Standard and Actual)
            LinearLayout consumptionLayout = new LinearLayout(this);
            consumptionLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Feed Intake
            TextView stdConsumptionView = new TextView(this);

            stdConsumptionView.setText(String.valueOf(record.getStandardFeedConsumption())); // Assuming FeedIntakeStd field exists
            stdConsumptionView.setPadding(150,0,0,0);

            stdConsumptionView.setText(record.getStandardFeedConsumption() != null ? String.valueOf(record.getStandardFeedConsumption()) : "N/A");

            consumptionLayout.addView(stdConsumptionView);

            // Actual Feed Intake
            TextView actualConsumptionView = new TextView(this);

            actualConsumptionView.setText(String.valueOf(record.getFeedConsumption()));
            actualConsumptionView.setPadding(80,0,0,0);

            actualConsumptionView.setText(record.getFeedConsumption() != null ? String.valueOf(record.getFeedConsumption()) : "N/A");

            consumptionLayout.addView(actualConsumptionView);

           tableRow.addView(consumptionLayout);

           // Cumulative (Standard and Actual)
          /*  LinearLayout cumulativeLayout = new LinearLayout(this);
            cumulativeLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Cumulative
            TextView stdCumulativeView = new TextView(this);
            stdCumulativeView.setText(String.valueOf(record.getStandardCumulativeFeed()));
            stdCumulativeView.setPadding(130,0,0,0);
            stdCumulativeView.setText(record.getStandardCumulativeFeed() != null ? String.valueOf(record.getStandardCumulativeFeed()) : "N/A");
            cumulativeLayout.addView(stdCumulativeView);

            // Actual Cumulative
            TextView actualCumulativeView = new TextView(this);
            actualCumulativeView.setText(String.valueOf(record.getCumulativeFeed()));
            actualCumulativeView.setPadding(80,0,0,0);
            actualCumulativeView.setText(record.getCumulativeFeed() != null ? String.valueOf(record.getCumulativeFeed()) : "N/A");
            cumulativeLayout.addView(actualCumulativeView);

            tableRow.addView(cumulativeLayout);*/



           /* TextView bodyWeightActualView = new TextView(this);
            bodyWeightActualView.setText(String.valueOf(record.getBodyWeight()));
            bodyWeightActualView.setPadding(150,0,0,0);
            tableRow.addView(bodyWeightActualView);

            TextView dayGainActualView = new TextView(this);
            dayGainActualView.setText(String.valueOf(record.getStandardCumulativeFeed()));
            dayGainActualView.setPadding(100,0,0,0);
            tableRow.addView(dayGainActualView);*/


            // Body Weight (Standard and Actual)
            LinearLayout bodyWeightLayout = new LinearLayout(this);
            bodyWeightLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Body Weight
            TextView stdBodyWeightView = new TextView(this);
            stdBodyWeightView.setText(String.valueOf(record.getStandardBodyWeight()));
            stdBodyWeightView.setPadding(80,0,0,0);
            stdBodyWeightView.setText(record.getStandardBodyWeight() != null ? String.valueOf(record.getStandardBodyWeight()) : "N/A");
            bodyWeightLayout.addView(stdBodyWeightView);

            // Actual Body Weight
            TextView actualBodyWeightView = new TextView(this);
            actualBodyWeightView.setText(String.valueOf(record.getBodyWeight()));
            actualBodyWeightView.setPadding(70,0,0,0);
            actualBodyWeightView.setText(record.getBodyWeight() != null ? String.valueOf(record.getBodyWeight()) : "N/A");
            bodyWeightLayout.addView(actualBodyWeightView);

            tableRow.addView(bodyWeightLayout);

            // Day Gain (Standard and Actual)
            LinearLayout dayGainLayout = new LinearLayout(this);
            dayGainLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Day Gain
            TextView stdDayGainView = new TextView(this);
            stdDayGainView.setText(String.valueOf(record.getStandardDayGain()));
            stdDayGainView.setPadding(80,0,0,0);
            stdDayGainView.setText(record.getStandardDayGain() != null ? String.valueOf(record.getStandardDayGain()) : "N/A");
            dayGainLayout.addView(stdDayGainView);

            // Actual Day Gain
            TextView actualDayGainView = new TextView(this);
            actualDayGainView.setText(String.valueOf(record.getDayGain()));
            actualDayGainView.setPadding(80,0,0,0);
            actualDayGainView.setText(record.getDayGain() != null ? String.valueOf(record.getDayGain()) : "N/A");
            dayGainLayout.addView(actualDayGainView);

            tableRow.addView(dayGainLayout);

            // FCR (Standard and Actual)


           /* LinearLayout fcrLayout = new LinearLayout(this);
            fcrLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Day Gain
            TextView stdFCRView = new TextView(this);
            stdFCRView.setText(String.valueOf(record.getStandardFCR()));
            stdFCRView.setPadding(80,0,0,0);
            stdFCRView.setText(record.getStandardFCR() != null ? String.valueOf(record.getStandardFCR()) : "N/A");
            fcrLayout.addView(stdFCRView);

            // Actual Day Gain
            TextView actualFCRView = new TextView(this);
            actualFCRView.setText(String.valueOf(record.getFCR()));
            actualFCRView.setPadding(80,0,0,0);
            actualFCRView.setText(record.getFCR() != null ? String.valueOf(record.getFCR()) : "N/A");
            fcrLayout.addView(actualFCRView);

            tableRow.addView(fcrLayout);*/

            // Actual FCR
            TextView medicineView = new TextView(this);
            medicineView.setText(String.valueOf(record.getMedicine()));
            medicineView.setPadding(80,0,0,0);
            medicineView.setText(record.getMedicine() != null ? String.valueOf(record.getMedicine()) : "N/A");
            tableRow.addView(medicineView);

            TextView vaccineView = new TextView(this);
            vaccineView.setText(String.valueOf(record.getVaccine()));
            vaccineView.setPadding(80,0,0,0);
            vaccineView.setText(record.getVaccine() != null ? String.valueOf(record.getVaccine()) : "N/A");
            tableRow.addView(vaccineView);

            TextView waterConsumptionView = new TextView(this);
            waterConsumptionView.setText(String.valueOf(record.getWaterConsumption()));
            waterConsumptionView.setPadding(130,0,0,0);
            waterConsumptionView.setText(record.getWaterConsumption() != null ? String.valueOf(record.getWaterConsumption()) : "N/A");
            tableRow.addView(waterConsumptionView);

          //  tableRow.addView(fcrLayout);

            // Add the completed table row to the TableLayout
           tableLayout.addView(tableRow);
        }

































        // Initialize views
      /*  batchListDetailRecyclerView = findViewById(R.id.recyclerview_batch_details);

        // Get Batch ID from intent
        Intent intent = getIntent();
        String batchId = intent.getStringExtra("BatchId");

        if (batchId == null) {
            Toast.makeText(this, "Batch ID not found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Setup Retrofit
        RetrofitInstance.BASEURL = "http://gallus.innovaneers.in/";
        batchIdModel = new BatchIdModel(batchId); // Pass batchId to model if needed

        // Fetch data
        fetchBatchDetails();
    }

    private void fetchBatchDetails() {
        Call<List<RecordRequestModel>> call = RetrofitInstance.getInstance().getMyApi().recordsHistory(batchIdModel);
        call.enqueue(new Callback<List<RecordRequestModel>>() {
            @Override
            public void onResponse(Call<List<RecordRequestModel>> call, Response<List<RecordRequestModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<RecordRequestModel> recordList = response.body();
                    setupRecyclerView(recordList);
                } else {
                    handleError(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<RecordRequestModel>> call, Throwable t) {
                handleError(t.getMessage());
            }
        });
    }

    private void setupRecyclerView(List<RecordRequestModel> recordList) {
        batchListDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        BatchDetailsAdapter adapter = new BatchDetailsAdapter(this, recordList);
        batchListDetailRecyclerView.setAdapter(adapter);
    }

    private void handleError(String errorMessage) {
        Toast.makeText(BatchDetailsActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
        Log.e("BatchDetailActivity", errorMessage);
    }*/
    }
}
