package in.innovaneers.gallus.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.innovaneers.gallus.MainActivity;
import in.innovaneers.gallus.R;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {
    private final Context context;
    private final List<PlanModel> planModels;

    public PlanAdapter(Context context, List<PlanModel> planModels) {
        this.context = context;
        this.planModels = planModels;
    }

    @NonNull
    @Override
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {
        final PlanModel model = planModels.get(position);
        holder.offer_title_text.setText(model.getTitle());
        holder.amount_text_plan.setText(model.getAmount());
        holder.validaity_text_plan.setText(model.getValidity());
        holder.discount_plan.setText("₹599");
        holder.background_img_plan.setImageResource(model.getImage());
        holder.discount_plan.setPaintFlags(holder.discount_plan.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MainActivity.class);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return planModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView offer_text ,amount_text_plan,validaity_text_plan,choose_text_plan,offer_title_text,discount_plan;
        ImageView img_offer,background_img_plan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            offer_text = itemView.findViewById(R.id.offer_text);
            amount_text_plan = itemView.findViewById(R.id.amount_text_plan);
            validaity_text_plan = itemView.findViewById(R.id.validaity_text_plan);
            choose_text_plan = itemView.findViewById(R.id.choose_text_plan);
            offer_title_text = itemView.findViewById(R.id.offer_title_text);
            background_img_plan = itemView.findViewById(R.id.background_img_plan);
            discount_plan = itemView.findViewById(R.id.discount_plan);
        }
    }
}
