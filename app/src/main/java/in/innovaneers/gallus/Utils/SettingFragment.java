package in.innovaneers.gallus.Utils;

import android.os.Bundle;

import static android.content.Context.MODE_PRIVATE;
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

import in.innovaneers.gallus.AboutUsActivity;
import in.innovaneers.gallus.PrivacyPolicyActivity;
import in.innovaneers.gallus.ProductDetailsActivity;
import in.innovaneers.gallus.R;


import android.widget.Button;
import android.widget.TextView;

import in.innovaneers.gallus.CCAvenueActivity;
import in.innovaneers.gallus.ContactUsActivity;
import in.innovaneers.gallus.FarmListActivity;
import in.innovaneers.gallus.LoginActivity;
import in.innovaneers.gallus.R;
import in.innovaneers.gallus.RecordsHistoryActivity;
import in.innovaneers.gallus.TermAndConditionActivity;

public class SettingFragment extends Fragment {


    TextView farmList_setting,logout_link_setting,username_account,term_setting,about_Setting,privacy_setting,product_setting;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";
    CardView help_contact_us_setting,payment_setting,record_card_setting;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        shp = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_NAME, "");
        username_account = view.findViewById(R.id.username_setting);
        username_account.setText(registeredUserNumber);

        payment_setting = view.findViewById(R.id.payment_setting);
        payment_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), CCAvenueActivity.class);
                startActivity(i);
            }
        });
        help_contact_us_setting = view.findViewById(R.id.help_contact_us_setting);
        help_contact_us_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ContactUsActivity.class);
                startActivity(i);
            }
        });
        record_card_setting = view.findViewById(R.id.record_card_setting);
        record_card_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), RecordsHistoryActivity.class);
                startActivity(i);
            }
        });
        term_setting = view.findViewById(R.id.term_setting);
        term_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), TermAndConditionActivity.class);
                startActivity(i);
            }
        });
        about_Setting = view.findViewById(R.id.about_Setting);
        about_Setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AboutUsActivity.class);
                startActivity(i);
            }
        });
        privacy_setting = view.findViewById(R.id.privacy_setting);
        privacy_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PrivacyPolicyActivity.class);
                startActivity(i);
            }
        });
        product_setting = view.findViewById(R.id.product_setting);
        product_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ProductDetailsActivity.class);
                startActivity(i);
            }
        });



        farmList_setting = view.findViewById(R.id.farmList_setting);
        farmList_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  i = new Intent(getContext(), FarmListActivity.class);
                startActivity(i);
            }
        });
        logout_link_setting = view.findViewById(R.id.logout_link_setting);
        logout_link_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLogoutDialog();
            }
        });
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