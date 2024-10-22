package in.innovaneers.gallus.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import in.innovaneers.gallus.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    private List<ProductModel> productModels;


    public ProductAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;

    }


    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        final ProductModel model = productModels.get(position);
        holder.textView.setText(model.getTitle());
       // Glide.with(context).load(model.getImage()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  Intent i = new Intent(context, OptionDetailShow.class);
              //  context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return productModels == null ? 0 : productModels.size();
        //return planModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.category_title);
            imageView = (ImageView)itemView.findViewById(R.id.category_image);
        }
    }
}
