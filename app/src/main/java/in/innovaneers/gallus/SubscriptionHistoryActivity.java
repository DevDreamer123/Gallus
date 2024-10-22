package in.innovaneers.gallus;

import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.innovaneers.gallus.model.FarmIdModel;
import in.innovaneers.gallus.model.FarmerIdModel;
import in.innovaneers.gallus.model.PlanAdapter;
import in.innovaneers.gallus.model.PlanModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import in.innovaneers.gallus.model.SubscriptionAdapter;
import in.innovaneers.gallus.model.SubscriptionModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscriptionHistoryActivity extends AppCompatActivity {

    RecyclerView recyclerViewSubscriptionHistory;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    public static String farmerID;
    TextView history_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_subscription_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        history_text = findViewById(R.id.history_text);
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserId = shp.getString(KEY_FARMER_ID, "");
        if (registeredUserId != null && !registeredUserId.isEmpty()) {
            farmerID = registeredUserId;
        } else {
            //userName_home.setText("Unknown User");
        }
        recyclerViewSubscriptionHistory = findViewById(R.id.recyclerViewSubscriptionHistory);
        FarmerIdModel farmIdModel =  new FarmerIdModel(farmerID);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";
        try {
            Call<List<SubscriptionModel>> lcall = RetrofitInstance.getInstance().getMyApi().createPlanSubscriptionHistory(farmIdModel);
            lcall.enqueue(new Callback<List<SubscriptionModel>>() {
                @Override
                public void onResponse(Call<List<SubscriptionModel>> call, Response<List<SubscriptionModel>> response) {
                    List<SubscriptionModel> itemModels = response.body();
                    if (response.body().isEmpty() || response.body() == null){
                        history_text.setVisibility(View.VISIBLE);
                    }else {
                        SubscriptionAdapter itemAdapter = new SubscriptionAdapter(itemModels, SubscriptionHistoryActivity.this);
                        recyclerViewSubscriptionHistory.setLayoutManager(new LinearLayoutManager(SubscriptionHistoryActivity.this, LinearLayoutManager.VERTICAL, false));
                        recyclerViewSubscriptionHistory.setAdapter(itemAdapter);
                        history_text.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Call<List<SubscriptionModel>> call, Throwable t) {
                    Log.d("error", t.getMessage());

                    t.toString();

                }
            });
        }catch (Exception e){

        }



    }
}