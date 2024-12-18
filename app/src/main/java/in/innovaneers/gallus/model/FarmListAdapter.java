package in.innovaneers.gallus.model;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.function.Consumer;

import in.innovaneers.gallus.R;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.innovaneers.gallus.FarmListActivity;
import in.innovaneers.gallus.MainActivity;
import in.innovaneers.gallus.R;
import in.innovaneers.gallus.Utils.HomeFragment;
import in.innovaneers.gallus.Utils.SettingFragment;
import retrofit2.Callback;

public class FarmListAdapter extends RecyclerView.Adapter<FarmListAdapter.ViewHolder> {
    private Context context;
    private List<FarmsModel> farmsModels;
    private SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";

    public FarmListAdapter(Context context, List<FarmsModel> farmsModels) {
        this.context = context;
        this.farmsModels = farmsModels;


    }
    @NonNull
    @Override
    public FarmListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.farm_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmListAdapter.ViewHolder holder, int position) {
        final FarmsModel farmsModel = farmsModels.get(position);
        if (position == farmsModels.size() - 1) {
            String LatestFarmId = farmsModel.getFarmID();
            Log.d("SendBroadcast", "LatestFarmId: " + LatestFarmId);

            // Save in SharedPreferences
            // Use SharedPrefHelper to save selected farm data
            SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(context);
            sharedPrefHelper.saveSelectedFarm(farmsModel.getFarmID(), farmsModel.getName());
        }
        holder.name_list_farm.setText(farmsModel.getName());
        holder.chicks_list_farm.setText(String.valueOf(farmsModel.getChicks()));
        holder.address_list_farm.setText(farmsModel.getAddress());
        holder.farmerid_list_farm.setText(farmsModel.getFarmerID());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save selected farm ID and name in SharedPreferences
                Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();

                // Use SharedPrefHelper to save selected farm data
                SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(context);
                sharedPrefHelper.saveSelectedFarm(farmsModel.getFarmID(), farmsModel.getName());

                // Log the selected farm data for debugging
                Log.d("FarmIdAdapter", farmsModel.getFarmID());
                Log.d("FarmNameAdapter", farmsModel.getName());

                // Start MainActivity or any other activity if required
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("formFarmSelection", true);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return farmsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name_list_farm,chicks_list_farm,address_list_farm,farmerid_list_farm;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_list_farm = itemView.findViewById(R.id.name_list_farm);
            chicks_list_farm = itemView.findViewById(R.id.chicks_list_farm);
            address_list_farm = itemView.findViewById(R.id.address_list_farm);
            farmerid_list_farm = itemView.findViewById(R.id.farmerid_list_farm);


        }
    }

}
