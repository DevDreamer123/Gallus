package in.innovaneers.gallus.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.function.Consumer;

import in.innovaneers.gallus.R;

public class FarmListAdapter extends RecyclerView.Adapter<FarmListAdapter.ViewHolder> {
    private Context context;
    private List<FarmsModel> farmsModels;

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
        holder.name_list_farm.setText(farmsModel.getName());
        holder.chicks_list_farm.setText(farmsModel.getChicks());
        holder.address_list_farm.setText(farmsModel.getAddress());
        holder.farmerid_list_farm.setText(farmsModel.getFarmerID());
        String farmid = farmsModel.getFarmID();



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
