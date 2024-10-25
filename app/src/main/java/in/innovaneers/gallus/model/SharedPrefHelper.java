package in.innovaneers.gallus.model;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefHelper {
    public static final String SHARED_PREF_NAME = "Gallus"; // SharedPreferences file name
    private static final String KEY_FARM_ID = "selectedFarmId";
    private static final String KEY_FARM_NAME = "selectedFarmName";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // Constructor to initialize SharedPreferences
    public SharedPrefHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    // Method to save farm ID and name
    public void saveSelectedFarm(String farmId, String farmName) {
        editor.putString(KEY_FARM_ID, farmId);
        editor.putString(KEY_FARM_NAME, farmName);
        editor.apply();  // Apply changes asynchronously
    }

    // Method to retrieve the saved farm ID
    public String getSelectedFarmId() {
        return sharedPreferences.getString(KEY_FARM_ID, null);  // Default value is null if not found
    }

    // Method to retrieve the saved farm name
    public String getSelectedFarmName() {
        return sharedPreferences.getString(KEY_FARM_NAME, null);  // Default value is null if not found
    }

    // Optional: Method to clear the saved farm info (if needed)
    public void clearSelectedFarm() {
        editor.remove(KEY_FARM_ID);
        editor.remove(KEY_FARM_NAME);
        editor.apply();
    }
}
