package in.innovaneers.gallus.Utils;

import static android.content.Context.MODE_PRIVATE;
import static in.innovaneers.gallus.LoginActivity.KEY_MOBILE;
import static in.innovaneers.gallus.LoginActivity.KEY_NAME;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import in.innovaneers.gallus.DailyRecordActivity;
import in.innovaneers.gallus.FarmAddActivity;
import in.innovaneers.gallus.FarmListActivity;
import in.innovaneers.gallus.R;
import in.innovaneers.gallus.model.BatchRequestModel;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);





        shp = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_NAME, "");
        userName_home = view.findViewById(R.id.userName_home);
        userName_home.setText(registeredUserNumber);


        btnAddExpense = view.findViewById(R.id.btnAddExpense);
        btnAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), FarmAddActivity.class);
                startActivity(i);
            }
        });
        add_record = view.findViewById(R.id.add_record);
        add_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), DailyRecordActivity.class);
                startActivity(i);
            }
        });
        show_farm_list_btn = view.findViewById(R.id.show_farm_list_btn);
        show_farm_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), FarmListActivity.class);
                startActivity(i);
            }
        });
        add_batch_home = view.findViewById(R.id.add_batch_home);
        add_batch_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPopup();
            }
        });


        return view;


    }

    private void openPopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.popup_layout, null);
        builder.setView(dialogView);




        chicks_no_edit_popup = dialogView.findViewById(R.id.chicks_no_edit_popup);
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
                String purchase = purchase_edit_popup.getText().toString();
                BatchRequestModel model = new BatchRequestModel("2088c724-fcb6-464f-a050-d07004f2a63a",chicks,purchase);
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

        // Close button click listener
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });







    }
}