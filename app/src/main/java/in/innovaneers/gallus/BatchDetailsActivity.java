package in.innovaneers.gallus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.innovaneers.gallus.model.BatchDetailsAdapter;
import in.innovaneers.gallus.model.BatchIdModel;
import in.innovaneers.gallus.model.RecordRequestModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatchDetailsActivity extends AppCompatActivity {
    private RecyclerView batchListDetailRecyclerView;
    private BatchIdModel batchIdModel;
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
        // Initialize views
        batchListDetailRecyclerView = findViewById(R.id.recyclerview_batch_details);

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
    }
    }
