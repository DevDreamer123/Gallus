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

public class BatchListAdapter extends RecyclerView.Adapter<BatchListAdapter.ViewHolder> {
    private Context context;
    private List<BatchListModel> batchListModels;

    public BatchListAdapter(Context context, List<BatchListModel> batchListModels) {
        this.context = context;
        this.batchListModels = batchListModels;
    }

    @NonNull
    @Override
    public BatchListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.batch_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BatchListAdapter.ViewHolder holder, int position) {
        final BatchListModel batchListModel = batchListModels.get(position);
        holder.totalcost_batch_adapter.setText(batchListModel.getTotalCost());
        holder.bodyweight_batch_adapter.setText(batchListModel.getBodyWeight());
        holder.date_batch_adpter.setText(batchListModel.getDate());
        holder.chicks_batch_adapter.setText(batchListModel.getChicks());

    }

    @Override
    public int getItemCount() {
        return batchListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView totalcost_batch_adapter,bodyweight_batch_adapter,date_batch_adpter,chicks_batch_adapter;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            totalcost_batch_adapter = itemView.findViewById(R.id.totalcost_batch_adapter);
            bodyweight_batch_adapter = itemView.findViewById(R.id.bodyweight_batch_adapter);
            date_batch_adpter = itemView.findViewById(R.id.date_batch_adpter);
            chicks_batch_adapter = itemView.findViewById(R.id.chicks_batch_adapter);
        }
    }
}
