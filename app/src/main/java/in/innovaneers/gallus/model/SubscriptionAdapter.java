package in.innovaneers.gallus.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.innovaneers.gallus.R;
import in.innovaneers.gallus.SubscriptionHistoryActivity;
public class SubscriptionAdapter extends RecyclerView .Adapter<SubscriptionAdapter.ViewHolder> {

    private List<SubscriptionModel> subscriptionList;
    private Context context;

    public SubscriptionAdapter(List<SubscriptionModel> subscriptionList,Context context) {
        this.subscriptionList = subscriptionList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubscriptionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subscription, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionAdapter.ViewHolder holder, int position) {
        SubscriptionModel subscription = subscriptionList.get(position);
        holder.offer_title_text_his.setText(subscription.getPlanID());
        //  holder.tvSubscriptionDate.setText("Subscribed on: " + subscription.getValidity());
        String Date = Method.UnixToDate(subscription.getValidTill());
        holder.validaity_text_plan_his.setText("Expires on: " + Date);
        Toast.makeText(context,Date, Toast.LENGTH_SHORT).show();
        holder.amount_text_plan_his.setText("Price: ₹" + subscription.getAmount());
    }

    @Override
    public int getItemCount() {
        return subscriptionList == null ? 0 : subscriptionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView offer_title_text_his, amount_text_plan_his, validaity_text_plan_his;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            offer_title_text_his = itemView.findViewById(R.id.offer_title_text_his);
            amount_text_plan_his = itemView.findViewById(R.id.amount_text_plan_his);
            validaity_text_plan_his = itemView.findViewById(R.id.validaity_text_plan_his);


        }
    }
}
