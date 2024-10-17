package in.innovaneers.gallus;

import static android.content.Context.MODE_PRIVATE;
import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;
import static in.innovaneers.gallus.LoginActivity.KEY_MOBILE;
import static in.innovaneers.gallus.LoginActivity.KEY_NAME;
import static in.innovaneers.gallus.LoginActivity.KEY_PASSWORD;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class AccountFragment extends Fragment {

    Button logout_profile_btn_account;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    TextView user_account,farmer_Id_account;
    CardView pi_account_btn,sp_account_btn,cp_account_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_account, container, false);

        user_account = view.findViewById(R.id.user_account);
        farmer_Id_account = view.findViewById(R.id.farmer_Id_account);
        shp = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_NAME, "");
        String registeredUserID = shp.getString(KEY_FARMER_ID, "");
        if (registeredUserNumber != null && !registeredUserNumber.isEmpty()) {
            user_account.setText(registeredUserNumber);
            farmer_Id_account.setText(registeredUserID);
        } else {
            user_account.setText("Unknown User");
            farmer_Id_account.setText("No ID");
        }


        logout_profile_btn_account = view.findViewById(R.id.logout_profile_btn_account);
        logout_profile_btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutDialog();
            }
        });


        //Card View Button
        pi_account_btn = view.findViewById(R.id.pi_account_btn);
        pi_account_btn.setOnClickListener(view1 -> {
            Intent i = new Intent(getContext() , ProfileActivity.class);
            startActivity(i);
        });
        sp_account_btn = view.findViewById(R.id.sp_account_btn);
        sp_account_btn.setOnClickListener(view1 -> {
            Intent i = new Intent(getContext(), SubscriptionHistoryActivity.class);
            startActivity(i);
        });
        cp_account_btn = view.findViewById(R.id.cp_account_btn);

        return view;
    }
    public void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.logout_popup_layout, null);
        builder.setView(dialogView);

        Button logoutButton = dialogView.findViewById(R.id.button_ok);
        Button cancelButton = dialogView.findViewById(R.id.button_no);

        final AlertDialog dialog = builder.create();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform logout action
                if (shp == null)
                    shp = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = shp.edit();
                editor.clear();
                editor.apply();
                editor.putString(KEY_MOBILE, "");
                editor.putString(KEY_PASSWORD, "");
                editor.commit();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();

                // For example, you can clear session data, navigate to login screen, etc.
                dialog.dismiss(); // Dismiss the dialog
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss(); // Dismiss the dialog
            }
        });

        dialog.show();
    }
}