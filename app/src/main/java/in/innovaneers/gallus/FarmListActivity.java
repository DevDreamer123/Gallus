package in.innovaneers.gallus;

import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.function.Consumer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.innovaneers.gallus.Utils.HomeFragment;
import in.innovaneers.gallus.model.FarmListAdapter;
import in.innovaneers.gallus.model.FarmerIdModel;
import in.innovaneers.gallus.model.FarmsModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FarmListActivity extends AppCompatActivity  {
RecyclerView farm_list_recycler;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    Button btnAddExpense;
    private static String globalFarmerId;

    private List<FarmsModel> farmsModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_farm_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        shp = getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String farmerId =shp.getString(KEY_FARMER_ID,"");

        String selectedFarmId = shp.getString("selectedFarmId","");
        String selectedName = shp.getString("selectedFarmName","");
        Toast.makeText(this,selectedFarmId+selectedName, Toast.LENGTH_SHORT).show();

        farmsModels = new ArrayList<>();
        globalFarmerId = farmerId;
        RetrofitInstance.BASEURL = "http://api.gallus.in/";
        FarmerIdModel farmerIdModel = new FarmerIdModel(globalFarmerId);
        try {
            Call<List<FarmsModel>> call = RetrofitInstance.getInstance().getMyApi().farmList(farmerIdModel);
            call.enqueue(new Callback<List<FarmsModel>>() {
                @Override
                public void onResponse(Call<List<FarmsModel>> call, Response<List<FarmsModel>> response) {
                    //  Toast.makeText(CategoryActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                    List<FarmsModel> farmsModels = response.body();
                    farm_list_recycler = findViewById(R.id.farm_list_recycler);
                    farm_list_recycler.setLayoutManager(new LinearLayoutManager(FarmListActivity.this, LinearLayoutManager.VERTICAL,false));
                    FarmListAdapter cate = new FarmListAdapter(FarmListActivity.this,farmsModels);
                    farm_list_recycler.setAdapter(cate);
                }


                @Override
                public void onFailure(Call<List<FarmsModel>> call, Throwable t) {
                    Toast.makeText(FarmListActivity.this,t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("error",t.getMessage());

                    t.toString();
                }
            });

        } catch (Exception e) {
            Toast.makeText(FarmListActivity.this,e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.d("error1",e.getMessage());
            e.getMessage();
        }


        //Farm Form clickListener
        btnAddExpense = findViewById(R.id.btnAddExpense);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FarmListActivity.this, FarmAddActivity.class);
                startActivity(i);
            }
        });

    }

}