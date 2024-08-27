package in.innovaneers.gallus.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.innovaneers.gallus.R;

public class RecordHistoryAdapter extends RecyclerView.Adapter<RecordHistoryAdapter.ViewHolder> {
    private Context context;
    private List<RecordRequestModel> recordRequestModels;

    public RecordHistoryAdapter(Context context, List<RecordRequestModel> recordRequestModels) {
        this.context = context;
        this.recordRequestModels = recordRequestModels;
    }

    @NonNull
    @Override
    public RecordHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_history_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHistoryAdapter.ViewHolder holder, int position) {
        final RecordRequestModel batchListModel = recordRequestModels.get(position);
        holder.MortalityTotal_list_record.setText(batchListModel.getMortalityTotal());
        holder.BodyWeight_list_record.setText(batchListModel.getBodyWeight());
        holder.WaterConsumption_list_record.setText(batchListModel.getWaterConsumption());
        holder.FeedConsumption_list_record.setText(batchListModel.getFeedConsumption());
    }

    @Override
    public int getItemCount() {
        return recordRequestModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView MortalityTotal_list_record,BodyWeight_list_record,WaterConsumption_list_record,FeedConsumption_list_record;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            MortalityTotal_list_record = itemView.findViewById(R.id.MortalityTotal_list_record);
            BodyWeight_list_record = itemView.findViewById(R.id.BodyWeight_list_record);
            WaterConsumption_list_record = itemView.findViewById(R.id.WaterConsumption_list_record);
            FeedConsumption_list_record = itemView.findViewById(R.id.FeedConsumption_list_record);
        }
    }
}
