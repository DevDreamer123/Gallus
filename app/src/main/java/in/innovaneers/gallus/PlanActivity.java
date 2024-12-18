package in.innovaneers.gallus;

import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;
import static in.innovaneers.gallus.LoginActivity.KEY_NAME;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.innovaneers.gallus.model.FarmIdModel;
import in.innovaneers.gallus.model.GetBatchIDModel;
import in.innovaneers.gallus.model.PlanAdapter;
import in.innovaneers.gallus.model.PlanModel;
import in.innovaneers.gallus.model.PlanPurchaseResponseModel;
import in.innovaneers.gallus.model.PlanRequestModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanActivity extends AppCompatActivity {

    RecyclerView plan_card_recycler;
    private PlanAdapter planAdapter;
    private List<PlanModel> planModels;
    public static String farmerID;

    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
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
        shp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserId = shp.getString(KEY_FARMER_ID, "");
        if (registeredUserId != null && !registeredUserId.isEmpty()) {
            farmerID = registeredUserId;
        } else {
            //userName_home.setText("Unknown User");
        }
        plan_card_recycler = findViewById(R.id.plan_card_recycler);
        RetrofitInstance.BASEURL = "http://api.gallus.in/";
        try {
            Call<List<PlanModel>> lcall = RetrofitInstance.getInstance().getMyApi().createPlanList();
            lcall.enqueue(new Callback<List<PlanModel>>() {
                @Override
                public void onResponse(Call<List<PlanModel>> call, Response<List<PlanModel>> response) {
                    List<PlanModel> itemModels = response.body();
                    PlanAdapter itemAdapter = new PlanAdapter(PlanActivity.this, itemModels,PlanActivity.this::showPlanDetailsDialog);
                    plan_card_recycler.setLayoutManager(new LinearLayoutManager(PlanActivity.this, LinearLayoutManager.VERTICAL, false));
                    plan_card_recycler.setAdapter(itemAdapter);
                }

                @Override
                public void onFailure(Call<List<PlanModel>> call, Throwable t) {
                    Log.d("error", t.getMessage());

                    t.toString();

                }
            });
        }catch (Exception e){

        }


       /* ArrayList<PlanModel> itemModels = new ArrayList<PlanModel>();
        itemModels.add(new PlanModel("3 Month Plan","₹499","Valid for 3 month",R.drawable.bluebgplan));
        itemModels.add(new PlanModel("6 Month Plan","₹899","Valid for 6 month",R.drawable.purplebg));
        itemModels.add(new PlanModel("1 Year Plan","₹1499","Valid for 1 year",R.drawable.skybg));
        PlanAdapter itemAdapter = new PlanAdapter(PlanActivity.this,itemModels);
        plan_card_recycler.setLayoutManager(new LinearLayoutManager(PlanActivity.this, LinearLayoutManager.VERTICAL,false));
        plan_card_recycler.setAdapter(itemAdapter);*/
    }
    private void showPlanDetailsDialog(PlanModel planModel){
        /*LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_plan_details,null);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
// Set data in the dialog
        TextView planName = dialogView.findViewById(R.id.planDetailName);
        TextView planPrice = dialogView.findViewById(R.id.planDetailPrice);
        TextView planDescription = dialogView.findViewById(R.id.planDetailDescription);
        Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);

        planName.setText(planModel.getTitle());
        planPrice.setText(planModel.getAmount());
        planDescription.setText(planModel.getValidity());

        // Handle confirm button click
        btnConfirm.setOnClickListener(v -> {
            // Handle confirmation (e.g., make a network request or save selection)
            Intent i = new Intent(PlanActivity.this,MainActivity.class);
            startActivity(i);
            alertDialog.dismiss();
        });

        // Show the dialog
        alertDialog.show();*/


        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_plan_details);

        // Initialize UI elements
        TextView popupTitle = dialog.findViewById(R.id.popupTitle);
        TextView planName = dialog.findViewById(R.id.planName);
        TextView planDuration = dialog.findViewById(R.id.planDuration);
        TextView planPrice = dialog.findViewById(R.id.planPrice);
        TextView paymentTotalDetails = dialog.findViewById(R.id.paymentTotalDetails);
        EditText couponCode = dialog.findViewById(R.id.couponCode);
        CheckBox termsCheckbox = dialog.findViewById(R.id.termsCheckbox);

        // Set plan details
        planName.setText("Plan Name: Subscription");
        planDuration.setText("Plan Duration: "+planModel.getTitle());
        planPrice.setText("Price: ₹"+planModel.getAmount());
        paymentTotalDetails.setText(planModel.getAmount());

        // Confirm button listener
        Button confirmButton = dialog.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(v -> {
            if (termsCheckbox.isChecked()) {
                // Process payment
                //GetBatchID
                Log.d("FarmerId",farmerID);
                Log.d("PlanId",planModel.getPlanID());
               RetrofitInstance.BASEURL = "http://api.gallus.in/";
                PlanRequestModel planRequestModel = new PlanRequestModel(farmerID,planModel.getPlanID());
                try {
                    Call<PlanPurchaseResponseModel> call = RetrofitInstance.getInstance().getMyApi().createPlanPurchase(planRequestModel);
                    call.enqueue(new Callback<PlanPurchaseResponseModel>() {
                        @Override
                        public void onResponse(Call<PlanPurchaseResponseModel> call, Response<PlanPurchaseResponseModel> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                PlanPurchaseResponseModel purchaseResponse = response.body();
                                Intent i = new Intent(PlanActivity.this,FarmAddActivity.class);
                                Toast.makeText(PlanActivity.this, purchaseResponse.getDescription(), Toast.LENGTH_LONG).show();
                                startActivity(i);
                                dialog.dismiss();

                            } else {
                                Toast.makeText(PlanActivity.this, "Response error"+response.body().getDescription(), Toast.LENGTH_SHORT).show();

                            }
                           // Log.d("URl",RetrofitInstance.BASEURL+"Plans/Purchase"+"?FarmerID="+farmerID+"&"+"PlanID="+planModel.getPlanID());
                        }

                        @Override
                        public void onFailure(Call<PlanPurchaseResponseModel> call, Throwable t) {
                            Log.e("API Error", t.getMessage());
                            Toast.makeText(PlanActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                Toast.makeText(this, "Please agree to the Terms and Conditions", Toast.LENGTH_SHORT).show();
            }
        });

        // Cancel button listener
        Button cancelButton = dialog.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();

    }
}