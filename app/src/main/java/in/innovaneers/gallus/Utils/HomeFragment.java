package in.innovaneers.gallus.Utils;

import static android.content.Context.MODE_PRIVATE;
import static in.innovaneers.gallus.LoginActivity.KEY_MOBILE;
import static in.innovaneers.gallus.LoginActivity.KEY_NAME;

import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;
import static in.innovaneers.gallus.LoginActivity.KEY_MOBILE;
import static in.innovaneers.gallus.LoginActivity.KEY_NAME;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.innovaneers.gallus.BatchListActivity;
import in.innovaneers.gallus.DailyRecordActivity;
import in.innovaneers.gallus.FarmAddActivity;
import in.innovaneers.gallus.FarmListActivity;
import in.innovaneers.gallus.R;
import in.innovaneers.gallus.RecordsHistoryActivity;
import in.innovaneers.gallus.model.BatchRequestModel;
import in.innovaneers.gallus.model.BatchIdModel;
import in.innovaneers.gallus.model.BatchRequestModel;
import in.innovaneers.gallus.model.DashBoardModel;
import in.innovaneers.gallus.model.FarmIdModel;
import in.innovaneers.gallus.model.FarmListAdapter;
import in.innovaneers.gallus.model.FarmerIdModel;
import in.innovaneers.gallus.model.FarmsModel;
import in.innovaneers.gallus.model.GetBatchIDModel;
import in.innovaneers.gallus.model.RegistrationResponseModel;
import in.innovaneers.gallus.model.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    Button btnAddExpense, add_record, show_farm_list_btn, add_batch_home;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    TextView userName_home;
    TextInputEditText chicks_no_edit_popup,purchase_edit_popup;
    AppCompatButton farmId_home;
    TextView rate_per_kg_home,fCR_per_kg_home,cpg_per_kg_home;
    TextInputEditText free_chicks_no_edit_popup,editTextDate,body_weight_text_edit_popup;
     String farmId;
     String currentBatchId;
    String selectedFarmId;
    AppCompatButton show_daily_records_btn;

    CardView add_record_card,show_farm_list_btn_card,add_batch_home_card,daily_record;
    public static String cuFarmName;
    public static String cuFarmId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String farmName = intent.getStringExtra("farm_name");
                cuFarmName = farmName;
                Log.d("CuFarmName",cuFarmName);

            }
        };

        IntentFilter filter = new IntentFilter("farm_name_action");
        getContext().registerReceiver(receiver, filter);

        BroadcastReceiver receiver1 = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String farmId = intent.getStringExtra("farm_id");
                 cuFarmId = farmId;
                Log.d("CuFarmId",farmId);

            }
        };

        IntentFilter filter1 = new IntentFilter("farm_id_action");
        getContext().registerReceiver(receiver1, filter1);

        rate_per_kg_home = view.findViewById(R.id.rate_per_kg_home);
        fCR_per_kg_home = view.findViewById(R.id.fCR_per_kg_home);
        cpg_per_kg_home = view.findViewById(R.id.cpg_per_kg_home);
        show_daily_records_btn = view.findViewById(R.id.show_daily_records_btn);
        show_daily_records_btn.setOnClickListener(view1 -> {
            Intent i = new Intent(getContext(), RecordsHistoryActivity.class);
            startActivity(i);
        });
        daily_record = view.findViewById(R.id.daily_record);
        daily_record.setOnClickListener(view1 -> {
            Intent i = new Intent(getContext(), RecordsHistoryActivity.class);
            startActivity(i);
        });



        shp = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_NAME, "");
        userName_home = view.findViewById(R.id.userName_home);
        if (registeredUserNumber != null && !registeredUserNumber.isEmpty()) {
            userName_home.setText(registeredUserNumber);
        } else {
            userName_home.setText("Unknown User");
        }


         selectedFarmId = shp.getString("selectedFarmId", "");
        String selectedName = shp.getString("selectedFarmName", "");

        if (selectedName != null && selectedFarmId != null) {
            Toast.makeText(getContext(), selectedName + selectedFarmId, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "No farm selected", Toast.LENGTH_SHORT).show();
        }
        userName_home = view.findViewById(R.id.userName_home);
        farmId_home = view.findViewById(R.id.farmId_home);
        userName_home.setText(registeredUserNumber);

        if (selectedName == null || selectedName.isEmpty()) {
            farmId_home.setText(R.string.select_farm);
        } else {
            farmId_home.setText(cuFarmName);
        }

      //  farmId_home.setText(selectedName);
        farmId_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),FarmListActivity.class);
                startActivity(i);
            }
        });



        add_record_card = view.findViewById(R.id.add_record_card);
        add_record_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), DailyRecordActivity.class);
                startActivity(i);
            }
        });

        /*show_farm_list_btn = view.findViewById(R.id.show_farm_list_btn);
        show_farm_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), BatchListActivity.class);
                startActivity(i);
            }
        });*/
        show_farm_list_btn_card = view.findViewById(R.id.show_farm_list_btn_card);
        show_farm_list_btn_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), BatchListActivity.class);
                startActivity(i);
            }
        });

        add_batch_home_card = view.findViewById(R.id.add_batch_home_card);
        add_batch_home_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopup();
            }
        });



        //GetBatchID
        RetrofitInstance.BASEURL = "http://api.gallus.in/";
        FarmIdModel farmIdModel = new FarmIdModel(cuFarmId);
        try {
            Call<GetBatchIDModel> call = RetrofitInstance.getInstance().getMyApi().GetBatchId(farmIdModel);
            call.enqueue(new Callback<GetBatchIDModel>() {
                @Override
                public void onResponse(Call<GetBatchIDModel> call, Response<GetBatchIDModel> response) {
                    GetBatchIDModel getBatchIDModel = response.body();
                    if (getBatchIDModel != null) {
                        currentBatchId = getBatchIDModel.getCurrentBatch();
                        //Log.d("API Response", currentBatchId);  // API से आया हुआ response
                        SharedPreferences.Editor editor = shp.edit();
                        editor.putString("currentBatchID", currentBatchId);
                        editor.apply();
                    } else {
                        Toast.makeText(getContext(), "Failed to get batch ID", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<GetBatchIDModel> call, Throwable t) {
                   // Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("error",t.getMessage());

                    t.toString();
                }
            });

        } catch (Exception e) {
            //Toast.makeText(getContext(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.d("error1",e.getMessage());
            e.getMessage();
        }


        //DashBoard Data
       RetrofitInstance.BASEURL = "http://api.gallus.in/";
        BatchIdModel batchIdModel = new BatchIdModel(currentBatchId);
        try {
            Call<DashBoardModel> call = RetrofitInstance.getInstance().getMyApi().dashBoard(batchIdModel);
            call.enqueue(new Callback<DashBoardModel>() {
                @Override
                public void onResponse(Call<DashBoardModel> call, Response<DashBoardModel> response) {
                    DashBoardModel dashBoardModel = response.body();
                    if (response.isSuccessful() && response.body() != null) {
                        if (dashBoardModel.getCostPerKG().isEmpty()) {
                            cpg_per_kg_home.setVisibility(View.VISIBLE);
                        } else {
                            cpg_per_kg_home.setText(dashBoardModel.getCostPerKG());
                        }

                        if (dashBoardModel.getFCR() == null) {
                            cpg_per_kg_home.setVisibility(View.VISIBLE);
                        } else {
                            fCR_per_kg_home.setText(dashBoardModel.getFCR());
                        }
                        if (dashBoardModel.getRatePerKG() == null) {
                            cpg_per_kg_home.setVisibility(View.VISIBLE);
                        } else {
                            rate_per_kg_home.setText(dashBoardModel.getRatePerKG());
                        }
                    }else {
                        handleError("API response was unsuccessfully");
                    }
                }


                @Override
                public void onFailure(Call<DashBoardModel> call, Throwable t) {
                    //Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("error",t.getMessage());

                    t.toString();
                }
            });

        } catch (Exception e) {
          //  Toast.makeText(getContext(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.d("error1",e.getMessage());
            e.getMessage();
        }



        return view;


    }

    private void openPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_layout, null);
        builder.setView(dialogView);






        editTextDate = dialogView.findViewById(R.id.date_text_edit_popup);
        chicks_no_edit_popup = dialogView.findViewById(R.id.chicks_no_edit_popup);
        free_chicks_no_edit_popup = dialogView.findViewById(R.id.free_chicks_no_edit_popup);
        body_weight_text_edit_popup = dialogView.findViewById(R.id.body_weight_text_edit_popup);
        purchase_edit_popup = dialogView.findViewById(R.id.purchase_edit_popup);
        Button resetButton = dialogView.findViewById(R.id.resetButton);
        Button closeButton = dialogView.findViewById(R.id.closeButton);

        final Dialog dialog = builder.create();
        dialog.show();
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // Reset all fields

                RetrofitInstance.BASEURL = "http://api.gallus.in/";

                // Get values from EditTexts and trim
                String chicks = chicks_no_edit_popup.getText().toString().trim();
                String free_chicks = free_chicks_no_edit_popup.getText().toString().trim();
                int Body_weight = Integer.parseInt(body_weight_text_edit_popup.getText().toString().trim());
                String purchase = purchase_edit_popup.getText().toString().trim();
                String date = editTextDate.getText().toString().trim();


                // Validate fields
                if (TextUtils.isEmpty(chicks) || TextUtils.isEmpty(purchase) || TextUtils.isEmpty(date)) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                BatchRequestModel model = new BatchRequestModel(selectedFarmId,chicks,free_chicks,Body_weight,date,purchase);
                try {
                    Call<RegistrationResponseModel> lcall = RetrofitInstance.getInstance().getMyApi().addBatch(model);
                    lcall.enqueue(new Callback<RegistrationResponseModel>() {
                        @Override
                        public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                RegistrationResponseModel showModel = response.body();
                                // Show success toast
                                addBatch(date);
                                Toast.makeText(getContext(), "Successfully Added", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();  // Dismiss dialog only on successful response
                            } else {
                                Log.d("error", "Response unsuccessful or null body");
                                Toast.makeText(getContext(), "Failed to Add", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                            // Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("error",t.getMessage());
                            Log.d("erroe2",t.getLocalizedMessage());
                            Log.d("erroe3",t.toString());
                            t.printStackTrace();


                        }
                    });


                }catch (Exception e){
                    e.getMessage();
                    e.toString();

                }


                // Reset Spinner
                //spinner.setSelection(0);
            }
        });
        editTextDate.setInputType(InputType.TYPE_NULL);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Close button click listener
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });







    }
    private void addBatch(String batchName){
        shp = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = shp.edit();
        editor.putString("batchDate",getCurrentDate());
        editor.apply();

    }
    private String getCurrentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        return sdf.format(new Date());
    }


    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Update TextInputEditText with selected date
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        editTextDate.setText(dateFormat.format(calendar.getTime()));
                        Toast.makeText(getContext(), "Date"+editTextDate.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }


private  void handleError(String errorMessage){
        Log.e("API Error", errorMessage);
    Toast.makeText(getActivity(), "Failed to load data", Toast.LENGTH_SHORT).show();
}

}