package in.innovaneers.gallus;

import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import in.innovaneers.gallus.model.BatchListAdapter;
import in.innovaneers.gallus.model.BatchListModel;
import in.innovaneers.gallus.model.FarmIdModel;
import in.innovaneers.gallus.model.FarmListAdapter;
import in.innovaneers.gallus.model.FarmerIdModel;
import in.innovaneers.gallus.model.FarmsModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatchListActivity extends AppCompatActivity {
    RecyclerView recycler_batch_list;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_batch_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        shp = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String farmerId =shp.getString(KEY_FARMER_ID,"");
        String selectedFarmId = shp.getString("selectedFarmId","");
        recycler_batch_list = findViewById(R.id.recycler_batch_list);
        Log.d("selectedFarmId",selectedFarmId);
        if (!farmerId.isEmpty() && !selectedFarmId.isEmpty()) {
            Log.d("selectFarmId", selectedFarmId);
            RetrofitInstance.BASEURL = "http://api.gallus.in/";
            FarmIdModel farmIdModel = new FarmIdModel(selectedFarmId);

            try {
                Call<List<BatchListModel>> call = RetrofitInstance.getInstance().getMyApi().ListBatch(farmIdModel);
                call.enqueue(new Callback<List<BatchListModel>>() {
                    @Override
                    public void onResponse(Call<List<BatchListModel>> call, Response<List<BatchListModel>> response) {
                        if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                            List<BatchListModel> farmsModels = response.body();
                            recycler_batch_list.setLayoutManager(new LinearLayoutManager(BatchListActivity.this, LinearLayoutManager.VERTICAL, false));
                            BatchListAdapter cate = new BatchListAdapter(BatchListActivity.this, farmsModels);
                            recycler_batch_list.setAdapter(cate);
                        } else {
                            Toast.makeText(BatchListActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                            Log.d("API Response", "Empty or null response received");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<BatchListModel>> call, Throwable t) {
                        Toast.makeText(BatchListActivity.this, "Failed to load data. Check your network.", Toast.LENGTH_SHORT).show();
                        Log.d("error", t.getMessage());
                    }
                });

            } catch (Exception e) {
                Toast.makeText(BatchListActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error1", e.getMessage());
            }
        } else {
            Toast.makeText(this, "Farmer ID or Farm ID missing", Toast.LENGTH_SHORT).show();
        }





    }
}