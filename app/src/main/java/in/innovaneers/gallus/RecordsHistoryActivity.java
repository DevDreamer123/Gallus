package in.innovaneers.gallus;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import in.innovaneers.gallus.model.BatchIdModel;
import in.innovaneers.gallus.model.BatchListAdapter;
import in.innovaneers.gallus.model.BatchListModel;
import in.innovaneers.gallus.model.DailyRecordHistoryModel;
import in.innovaneers.gallus.model.FarmIdModel;
import in.innovaneers.gallus.model.Method;
import in.innovaneers.gallus.model.RecordHistoryAdapter;
import in.innovaneers.gallus.model.RecordRequestModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecordsHistoryActivity extends AppCompatActivity {

    RecyclerView recycler_records_list;
    SharedPreferences shp;
    TextView current_batch_record_id;
    public static final String SHARED_PREF_NAME = "Gallus";
    private static final int STORAGE_PERMISSION_CODE = 101;
    private static String CurrentBatchId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_records_history);
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


        // Request storage permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        } else {
            fetchDataFromApi();
        }
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
                    Toast.makeText(RecordsHistoryActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RecordsHistoryActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                    if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                        //createExcelFile(response.body());
                        List<DailyRecordHistoryModel> dailyRecordHistoryModels = response.body();
                        if (dailyRecordHistoryModels != null) {
                            populateTable(dailyRecordHistoryModels);
                        }

                    } else {
                        Toast.makeText(RecordsHistoryActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                    }

                }


                @Override
                public void onFailure(Call<List<DailyRecordHistoryModel>> call, Throwable t) {
                    Toast.makeText(RecordsHistoryActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("error", t.getMessage());

                    t.toString();
                }
            });

        } catch (Exception e) {
            Toast.makeText(RecordsHistoryActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
            ageView.setText(record.getAge() != null ? String.valueOf(record.getAge()) : "N/A");
            tableRow.addView(ageView);

            // Housed Column
            TextView housedView = new TextView(this);
            housedView.setText(record.getHoused() != null ? String.valueOf(record.getHoused()) : "N/A");
            tableRow.addView(housedView);

            // Mortality (Daily/Total/Cum%)
            LinearLayout mortalityLayout = new LinearLayout(this);
            mortalityLayout.setOrientation(LinearLayout.VERTICAL);

            // Daily Mortality
            TextView dailyMortalityView = new TextView(this);
            dailyMortalityView.setText(record.getMortalityCount() != null ? String.valueOf(record.getMortalityCount()) : "N/A");
            mortalityLayout.addView(dailyMortalityView);

            // Total Mortality
            TextView totalMortalityView = new TextView(this);
            totalMortalityView.setText(record.getMortalityCount() != null ? String.valueOf(record.getMortalityCount()) : "N/A");
            mortalityLayout.addView(totalMortalityView);

            // Cumulative Mortality Percentage
            TextView cumMortalityView = new TextView(this);
            cumMortalityView.setText(record.getMortalityCount() != null ? String.valueOf(record.getMortalityCount()) : "N/A");
            mortalityLayout.addView(cumMortalityView);

            tableRow.addView(mortalityLayout);

            // Mortality (Standard and Actual)
            LinearLayout mortalityStdActualLayout = new LinearLayout(this);
            mortalityStdActualLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Mortality
            TextView stdMortalityView = new TextView(this);
            stdMortalityView.setText(record.getMortalityCount() != null ? String.valueOf(record.getMortalityCount()) : "N/A");
            mortalityStdActualLayout.addView(stdMortalityView);

            // Actual Mortality
            TextView actualMortalityView = new TextView(this);
            actualMortalityView.setText(record.getMortalityTotal() != null ? String.valueOf(record.getMortalityTotal()) : "N/A");
            mortalityStdActualLayout.addView(actualMortalityView);

            tableRow.addView(mortalityStdActualLayout);

            // Feed Intake (Standard and Actual)
            LinearLayout feedIntakeLayout = new LinearLayout(this);
            feedIntakeLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Feed Intake
            TextView stdFeedIntakeView = new TextView(this);
            stdFeedIntakeView.setText(record.getFeedBrand() != null ? String.valueOf(record.getFeedBrand()) : "N/A");
            feedIntakeLayout.addView(stdFeedIntakeView);

            // Actual Feed Intake
            TextView actualFeedIntakeView = new TextView(this);
            actualFeedIntakeView.setText(record.getFeedBrand() != null ? String.valueOf(record.getFeedBrand()) : "N/A");
            feedIntakeLayout.addView(actualFeedIntakeView);

            tableRow.addView(feedIntakeLayout);

            // Cumulative (Standard and Actual)
            LinearLayout cumulativeLayout = new LinearLayout(this);
            cumulativeLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Cumulative
            TextView stdCumulativeView = new TextView(this);
            stdCumulativeView.setText(record.getCumulativeFeed() != null ? String.valueOf(record.getCumulativeFeed()) : "N/A");
            cumulativeLayout.addView(stdCumulativeView);

            // Actual Cumulative
            TextView actualCumulativeView = new TextView(this);
            actualCumulativeView.setText(record.getCumulativeFeed() != null ? String.valueOf(record.getCumulativeFeed()) : "N/A");
            cumulativeLayout.addView(actualCumulativeView);

            tableRow.addView(cumulativeLayout);

            // Body Weight (Standard and Actual)
            LinearLayout bodyWeightLayout = new LinearLayout(this);
            bodyWeightLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Body Weight
            TextView stdBodyWeightView = new TextView(this);
            stdBodyWeightView.setText(record.getBodyWeight() != null ? String.valueOf(record.getBodyWeight()) : "N/A");
//actualBodyWeightView.setText(record.getActualBodyWeight() != null ? String.valueOf(record.getActualBodyWeight()) : "N/A");
            bodyWeightLayout.addView(stdBodyWeightView);

            // Actual Body Weight
            TextView actualBodyWeightView = new TextView(this);
            actualBodyWeightView.setText(record.getBodyWeight() != null ? String.valueOf(record.getBodyWeight()) : "N/A");
            bodyWeightLayout.addView(actualBodyWeightView);

            tableRow.addView(bodyWeightLayout);

            // Day Gain (Standard and Actual)
            LinearLayout dayGainLayout = new LinearLayout(this);
            dayGainLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard Day Gain
            TextView stdDayGainView = new TextView(this);
            stdDayGainView.setText(record.getMedicine() != null ? String.valueOf(record.getMedicine()) : "N/A");
            dayGainLayout.addView(stdDayGainView);

            // Actual Day Gain
            TextView actualDayGainView = new TextView(this);
            actualDayGainView.setText(record.getMedicine() != null ? String.valueOf(record.getMedicine()) : "N/A");
            dayGainLayout.addView(actualDayGainView);

            tableRow.addView(dayGainLayout);

            // FCR (Standard and Actual)
            LinearLayout fcrLayout = new LinearLayout(this);
            fcrLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Standard FCR
            TextView stdFcrView = new TextView(this);
            stdFcrView.setText(record.getDayGain() != null ? String.valueOf(record.getDayGain()) : "N/A");
            fcrLayout.addView(stdFcrView);

            // Actual FCR
            TextView actualFcrView = new TextView(this);
            actualFcrView.setText(record.getDayGain() != null ? String.valueOf(record.getDayGain()) : "N/A");
            fcrLayout.addView(actualFcrView);

            tableRow.addView(fcrLayout);

            // Add the completed table row to the TableLayout
            tableLayout.addView(tableRow);
        }

    }
}































    /*private void createExcelFile(List<DailyRecordHistoryModel> records) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Daily Record");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Date");
        headerRow.createCell(1).setCellValue("Age");
        headerRow.createCell(2).setCellValue("Housed");
        headerRow.createCell(3).setCellValue("Daily Mortality");
        headerRow.createCell(4).setCellValue("Total Mortality");
        headerRow.createCell(5).setCellValue("Cumulative Mortality (%)");
        headerRow.createCell(6).setCellValue("Feed Intake Std");
        headerRow.createCell(7).setCellValue("Feed Intake Actual");
        headerRow.createCell(8).setCellValue("Cum Feed Std");
        headerRow.createCell(9).setCellValue("Cum Feed Actual");
        headerRow.createCell(10).setCellValue("Body Weight Std");
        headerRow.createCell(11).setCellValue("Body Weight Actual");
        headerRow.createCell(12).setCellValue("Day Gain Std");
        headerRow.createCell(13).setCellValue("Day Gain Actual");
        headerRow.createCell(14).setCellValue("FCR Std");
        headerRow.createCell(15).setCellValue("FCR Actual");
        headerRow.createCell(16).setCellValue("Feed Received");
        headerRow.createCell(17).setCellValue("Feed Consumed");
        headerRow.createCell(18).setCellValue("Closing Stock");
        headerRow.createCell(19).setCellValue("Remarks");

        // Populate data rows
        int rowCount = 1;
        for (DailyRecordHistoryModel record : records) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(record.getDate());
            row.createCell(1).setCellValue(record.getAge());
            row.createCell(2).setCellValue(record.getHoused());
            row.createCell(3).setCellValue(record.getFeedBrand());
            row.createCell(4).setCellValue(record.getFeedConsumption());
            row.createCell(5).setCellValue(record.getBodyWeight());
            row.createCell(6).setCellValue(record.getDayGain());
            row.createCell(7).setCellValue(record.getFCR());
            row.createCell(8).setCellValue(record.me());
            row.createCell(9).setCellValue(record.getCumFeedActual());
            row.createCell(10).setCellValue(record.getBodyWeightStd());
            row.createCell(11).setCellValue(record.getBodyWeightActual());
            row.createCell(12).setCellValue(record.getDayGainStd());
            row.createCell(13).setCellValue(record.getDayGainActual());
            row.createCell(14).setCellValue(record.getFcrStd());
            row.createCell(15).setCellValue(record.getFcrActual());
            row.createCell(16).setCellValue(record.getFeedReceived());
            row.createCell(17).setCellValue(record.getFeedConsumed());
            row.createCell(18).setCellValue(record.getClosingStock());
            row.createCell(19).setCellValue(record.getRemarks());
        }

        // Save the Excel file
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "ChicksBatchRecords.xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            Toast.makeText(this, "Excel file created: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchDataFromApi();
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }*/












       /* recycler_records_list = findViewById(R.id.recycler_records_list);
        RetrofitInstance.BASEURL = "http://gallus.innovaneers.in/";
        BatchIdModel farmIdModel = new BatchIdModel(currentBatchId);
        try {
            Call<List<RecordRequestModel>> call = RetrofitInstance.getInstance().getMyApi().recordsHistory(farmIdModel);
            call.enqueue(new Callback<List<RecordRequestModel>>() {
                @Override
                public void onResponse(Call<List<RecordRequestModel>> call, Response<List<RecordRequestModel>> response) {
                    //Toast.makeText(RecordsHistoryActivity.this, "Hello", Toast.LENGTH_SHORT).show();

                    if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                        List<RecordRequestModel> farmsModels = response.body();
                        recycler_records_list.setLayoutManager(new LinearLayoutManager(RecordsHistoryActivity.this, LinearLayoutManager.VERTICAL,false));
                        RecordHistoryAdapter cate = new RecordHistoryAdapter(RecordsHistoryActivity.this,farmsModels);
                        recycler_records_list.setAdapter(cate);
                    } else {
                        Toast.makeText(RecordsHistoryActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                        Log.d("API Response", "Empty or null response received");
                    }

                }


                @Override
                public void onFailure(Call<List<RecordRequestModel>> call, Throwable t) {
                    Toast.makeText(RecordsHistoryActivity.this,t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("error",t.getMessage());

                    t.toString();
                }
            });

        } catch (Exception e) {
            Toast.makeText(RecordsHistoryActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.d("error1",e.getMessage());
            e.getMessage();
        }*/


