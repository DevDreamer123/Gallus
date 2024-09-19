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

public class BatchDetailsAdapter extends RecyclerView.Adapter<BatchDetailsAdapter.ViewHolder> {
    private Context context;
    private List<RecordRequestModel> recordRequestModels;

    public BatchDetailsAdapter(Context context, List<RecordRequestModel> recordRequestModels) {
        this.context = context;
        this.recordRequestModels = recordRequestModels;
    }

    @NonNull
    @Override
    public BatchDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.batch_details_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchDetailsAdapter.ViewHolder holder, int position) {
        final RecordRequestModel batchListModel = recordRequestModels.get(position);
        holder.mortality_count_text_details.setText(batchListModel.getMortalityCount());
        holder.mortality_total_text_details.setText(batchListModel.getMortalityTotal());
        holder.feed_brand_text_details.setText(batchListModel.getFeedBrand());
        holder.feed_consumption_text_details.setText(batchListModel.getFeedConsumption());
        holder.body_weight_text_details.setText(batchListModel.getBodyWeight());
        holder.water_consumption_text_details.setText(batchListModel.getWaterConsumption());
    }

    @Override
    public int getItemCount() {
        return recordRequestModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mortality_count_text_details,mortality_total_text_details,feed_brand_text_details,feed_consumption_text_details
                ,body_weight_text_details,water_consumption_text_details;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mortality_count_text_details = itemView.findViewById(R.id.mortality_count_text_details);
            mortality_total_text_details = itemView.findViewById(R.id.mortality_total_text_details);
            feed_brand_text_details = itemView.findViewById(R.id.feed_brand_text_details);
            feed_consumption_text_details = itemView.findViewById(R.id.feed_consumption_text_details);
            body_weight_text_details = itemView.findViewById(R.id.body_weight_text_details);
            water_consumption_text_details = itemView.findViewById(R.id.mortality_count_text_details);
        }
    }
}
