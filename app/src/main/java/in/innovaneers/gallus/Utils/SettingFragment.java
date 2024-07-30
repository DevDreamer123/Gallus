package in.innovaneers.gallus.Utils;

import static in.innovaneers.gallus.LoginActivity.KEY_MOBILE;
import static in.innovaneers.gallus.LoginActivity.KEY_PASSWORD;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import in.innovaneers.gallus.FarmListActivity;
import in.innovaneers.gallus.LoginActivity;
import in.innovaneers.gallus.R;

public class SettingFragment extends Fragment {


    TextView farmList_setting,logout_link_setting;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
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