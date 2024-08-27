package in.innovaneers.gallus;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.innovaneers.gallus.model.PlanAdapter;
import in.innovaneers.gallus.model.PlanModel;

public class PlanActivity extends AppCompatActivity {
RecyclerView plan_card_recycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_plan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        plan_card_recycler = findViewById(R.id.plan_card_recycler);

        ArrayList<PlanModel> itemModels = new ArrayList<PlanModel>();
        itemModels.add(new PlanModel("3 Month Plan","₹499","Valid for 3 month",R.drawable.bluebgplan));
        itemModels.add(new PlanModel("6 Month Plan","₹899","Valid for 6 month",R.drawable.purplebg));
        itemModels.add(new PlanModel("1 Year Plan","₹1499","Valid for 1 year",R.drawable.skybg));
        PlanAdapter itemAdapter = new PlanAdapter(PlanActivity.this,itemModels);
        plan_card_recycler.setLayoutManager(new LinearLayoutManager(PlanActivity.this, LinearLayoutManager.VERTICAL,false));
        plan_card_recycler.setAdapter(itemAdapter);
    }
}