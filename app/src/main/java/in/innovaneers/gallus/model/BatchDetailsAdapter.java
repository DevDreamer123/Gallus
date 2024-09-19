package in.innovaneers.gallus.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.innovaneers.gallus.BatchDetailActivity;
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
        holder.mortality_count_text_detail.setText(batchListModel.getMortalityCount());
        holder.mortality_total_text_detail.setText(batchListModel.getMortalityTotal());
        holder.feed_brand_text_detail.setText(batchListModel.getFeedBrand());
        holder.feed_consumption_text_detail.setText(batchListModel.getFeedConsumption());
        holder.body_weight_text_detail.setText(batchListModel.getBodyWeight());
        holder.water_consumption_text_detail.setText(batchListModel.getWaterConsumption());
    }

    @Override
    public int getItemCount() {
        return recordRequestModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView mortality_count_text_detail,mortality_total_text_detail,feed_brand_text_detail,feed_consumption_text_detail,body_weight_text_detail,water_consumption_text_detail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mortality_count_text_detail = itemView.findViewById(R.id.mortality_count_text_detail);
            mortality_total_text_detail = itemView.findViewById(R.id.mortality_total_text_detail);
            feed_brand_text_detail = itemView.findViewById(R.id.feed_brand_text_detail);
            feed_consumption_text_detail = itemView.findViewById(R.id.feed_consumption_text_detail);
            body_weight_text_detail = itemView.findViewById(R.id.body_weight_text_detail);
            water_consumption_text_detail = itemView.findViewById(R.id.water_consumption_text_detail);
        }
    }
}
