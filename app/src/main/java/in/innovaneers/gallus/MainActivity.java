package in.innovaneers.gallus;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

import in.innovaneers.gallus.Utils.HomeFragment;
import in.innovaneers.gallus.Utils.SettingFragment;






public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab,fab_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.yellow));
        }*/
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        float radius = getResources().getDimension(R.dimen.radius_corner);
        //BottomAppBar bottomAppBar = findViewById(R.id.bottomAppBar);

       /* MaterialShapeDrawable bottomBarBackground = (MaterialShapeDrawable) bottomAppBar.getBackground();
        bottomBarBackground.setShapeAppearanceModel(
                bottomBarBackground.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopRightCorner(CornerFamily.ROUNDED,radius)
                        .setTopLeftCorner(CornerFamily.ROUNDED,radius)
                        .build());*/
        showHomeFragment();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
      //  bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                int id = item.getItemId();

                if(id == R.id.home)
                {
                    transaction.replace(R.id.container, new HomeFragment());

                }
                //  if(id == R.id.order)
                //   {
                //       transaction.replace(R.id.container, new CategoryFragment());

                //   }
                if(id == R.id.person)
                {
                    transaction.replace(R.id.container, new AccountFragment());

                }
                transaction.commit();
                return true;
            }
        });
        int icon1color = ContextCompat.getColor(this,R.color.white);
        fab= findViewById(R.id.fab);
        fab.setColorFilter(icon1color, PorterDuff.Mode.SRC_IN);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SettingFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                Toast.makeText(MainActivity.this, "Cart", Toast.LENGTH_SHORT).show();
            }
        });
         fab_msg = findViewById(R.id.fab_msg);
       fab_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openWhatsApp("Hello!"); // Replace with your message

            }
        });
        //fab_msg.setOnClickListener(v -> openWhatsAppChat("1234567890", "Hello! I need help.")); // Use a valid phone number


    }

    private void showHomeFragment() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new HomeFragment());
        transaction.commit();
    }
    private void openWhatsApp(String message) {
        String phoneNumber = "+91 9919916661"; // Replace with the phone number you want to send the message to
        String url = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}