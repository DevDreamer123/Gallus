package in.innovaneers.gallus;

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

import in.innovaneers.gallus.model.BatchIdModel;
import in.innovaneers.gallus.model.BatchListAdapter;
import in.innovaneers.gallus.model.BatchListModel;
import in.innovaneers.gallus.model.FarmIdModel;
import in.innovaneers.gallus.model.RecordHistoryAdapter;
import in.innovaneers.gallus.model.RecordRequestModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordsHistoryActivity extends AppCompatActivity {
RecyclerView recycler_records_list;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
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

        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String currentBatchId = shp.getString("currentBatchID","");
        Log.d("currentBatchId",currentBatchId);


        recycler_records_list = findViewById(R.id.recycler_records_list);
        RetrofitInstance.BASEURL = "http://gallus.innovaneers.in/";
        BatchIdModel farmIdModel = new BatchIdModel(currentBatchId);
        try {
            Call<List<RecordRequestModel>> call = RetrofitInstance.getInstance().getMyApi().recordsHistory(farmIdModel);
            call.enqueue(new Callback<List<RecordRequestModel>>() {
                @Override
                public void onResponse(Call<List<RecordRequestModel>> call, Response<List<RecordRequestModel>> response) {
                    Toast.makeText(RecordsHistoryActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    List<RecordRequestModel> farmsModels = response.body();
                    recycler_records_list.setLayoutManager(new LinearLayoutManager(RecordsHistoryActivity.this, LinearLayoutManager.VERTICAL,false));
                    RecordHistoryAdapter cate = new RecordHistoryAdapter(RecordsHistoryActivity.this,farmsModels);
                    recycler_records_list.setAdapter(cate);
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
        }
    }
}