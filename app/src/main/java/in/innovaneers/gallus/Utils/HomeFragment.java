package in.innovaneers.gallus.Utils;

import static android.content.Context.MODE_PRIVATE;
import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;
import static in.innovaneers.gallus.LoginActivity.KEY_MOBILE;
import static in.innovaneers.gallus.LoginActivity.KEY_NAME;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.InputType;
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
import java.util.List;
import java.util.Locale;

import in.innovaneers.gallus.BatchListActivity;
import in.innovaneers.gallus.DailyRecordActivity;
import in.innovaneers.gallus.FarmAddActivity;
import in.innovaneers.gallus.FarmListActivity;
import in.innovaneers.gallus.R;
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

    Button  add_record, show_farm_list_btn, add_batch_home;
    AppCompatButton farmId_home;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    TextView userName_home,rate_per_kg_home,fCR_per_kg_home,cpg_per_kg_home;
    TextInputEditText chicks_no_edit_popup,purchase_edit_popup,free_chicks_no_edit_popup,editTextDate;
     String farmId;
     String currentBatchId;

    CardView add_record_card,show_farm_list_btn_card,add_batch_home_card;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        rate_per_kg_home = view.findViewById(R.id.rate_per_kg_home);
        fCR_per_kg_home = view.findViewById(R.id.fCR_per_kg_home);
        cpg_per_kg_home = view.findViewById(R.id.cpg_per_kg_home);



        shp = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_NAME, "");
        String farmerId = shp.getString(KEY_FARMER_ID,"");
        farmId = farmerId;
        String selectedFarmId = shp.getString("selectedFarmId","");
        String selectedName = shp.getString("selectedFarmName","");
        Toast.makeText(getContext(),selectedName+selectedFarmId, Toast.LENGTH_SHORT).show();
        userName_home = view.findViewById(R.id.userName_home);
        farmId_home = view.findViewById(R.id.farmId_home);
        userName_home.setText(registeredUserNumber);

        if (selectedName.isEmpty()) {
            farmId_home.setText(R.string.select_farm);
        } else {
            farmId_home.setText(selectedName);
        }

      //  farmId_home.setText(selectedName);
        farmId_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(),FarmListActivity.class);
                startActivity(i);
            }
        });



        /*add_record = view.findViewById(R.id.add_record);
        add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), DailyRecordActivity.class);
                startActivity(i);
            }
        });*/
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
        /*add_batch_home = view.findViewById(R.id.add_batch_home);
        add_batch_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopup();
            }
        });*/
        add_batch_home_card = view.findViewById(R.id.add_batch_home_card);
        add_batch_home_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopup();
            }
        });



        //GetBatchID
        RetrofitInstance.BASEURL = "http://gallus.innovaneers.in/";
        FarmIdModel farmIdModel = new FarmIdModel(selectedFarmId);
        try {
            Call<GetBatchIDModel> call = RetrofitInstance.getInstance().getMyApi().GetBatchId(farmIdModel);
            call.enqueue(new Callback<GetBatchIDModel>() {
                @Override
                public void onResponse(Call<GetBatchIDModel> call, Response<GetBatchIDModel> response) {
                    GetBatchIDModel getBatchIDModel = response.body();
                    currentBatchId = getBatchIDModel.getCurrentBatch();
                    Toast.makeText(getContext(),"CurrentBatchId" +currentBatchId, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = shp.edit();
                    editor.putString("currentBatchID", getBatchIDModel.getCurrentBatch());
                    editor.apply();
                }


                @Override
                public void onFailure(Call<GetBatchIDModel> call, Throwable t) {
                    Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("error",t.getMessage());

                    t.toString();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getContext(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.d("error1",e.getMessage());
            e.getMessage();
        }


        //DashBoard Data
       RetrofitInstance.BASEURL = "http://gallus.innovaneers.in/";
        BatchIdModel batchIdModel = new BatchIdModel(currentBatchId);
        try {
            Call<DashBoardModel> call = RetrofitInstance.getInstance().getMyApi().dashBoard(batchIdModel);
            call.enqueue(new Callback<DashBoardModel>() {
                @Override
                public void onResponse(Call<DashBoardModel> call, Response<DashBoardModel> response) {
                    DashBoardModel dashBoardModel = response.body();
                    cpg_per_kg_home.setText(dashBoardModel.getCostPerKG());
                    fCR_per_kg_home.setText(dashBoardModel.getFCR());
                    rate_per_kg_home.setText(dashBoardModel.getRatePerKG());
                }


                @Override
                public void onFailure(Call<DashBoardModel> call, Throwable t) {
                    Toast.makeText(getContext(),t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("error",t.getMessage());

                    t.toString();
                }
            });

        } catch (Exception e) {
            Toast.makeText(getContext(),e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
        purchase_edit_popup = dialogView.findViewById(R.id.purchase_edit_popup);
        Button resetButton = dialogView.findViewById(R.id.resetButton);
        Button closeButton = dialogView.findViewById(R.id.closeButton);

        final Dialog dialog = builder.create();
        dialog.show();
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitInstance.BASEURL = "http://gallus.innovaneers.in/";
                String chicks = chicks_no_edit_popup.getText().toString();
                String free_chicks = free_chicks_no_edit_popup.getText().toString();
                String purchase = purchase_edit_popup.getText().toString();
                BatchRequestModel model = new BatchRequestModel(farmId,chicks,free_chicks,editTextDate.toString(),purchase);
                try {
                    Call<RegistrationResponseModel> lcall = RetrofitInstance.getInstance().getMyApi().addBatch(model);
                    lcall.enqueue(new Callback<RegistrationResponseModel>() {
                        @Override
                        public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                            RegistrationResponseModel showModel= response.body();
                            Toast.makeText(getContext(), response.body().getStatus(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                            Toast.makeText(getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
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




                // Reset all fields


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






}