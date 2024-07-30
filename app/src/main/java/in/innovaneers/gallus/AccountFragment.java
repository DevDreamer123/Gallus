package in.innovaneers.gallus;

import static android.content.Context.MODE_PRIVATE;
import static in.innovaneers.gallus.LoginActivity.KEY_FARMER_ID;
import static in.innovaneers.gallus.LoginActivity.KEY_NAME;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AccountFragment extends Fragment {

    TextView user_account,farmer_Id_account;
    SharedPreferences shp;
    public static final String SHARED_PREF_NAME = "Gallus";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_account, container, false);
        shp = getActivity().getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String registeredUserNumber = shp.getString(KEY_NAME, "");
        String registerFarmerID = shp.getString(KEY_FARMER_ID, "");
        user_account = view.findViewById(R.id.user_account);
        farmer_Id_account = view.findViewById(R.id.farmer_Id_account);
       user_account.setText(registeredUserNumber);
        farmer_Id_account.setText(registerFarmerID);
       return view;
    }
}