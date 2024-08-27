package in.innovaneers.gallus;

<<<<<<< HEAD
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
=======
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
>>>>>>> ef3c6549b89830f91825af27b024f24cc9ef2d1f
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
<<<<<<< HEAD
    FloatingActionButton fab,fab_msg;
=======
    FloatingActionButton fab;
>>>>>>> ef3c6549b89830f91825af27b024f24cc9ef2d1f
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
<<<<<<< HEAD
=======
     /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.yellow));
        }*/
>>>>>>> ef3c6549b89830f91825af27b024f24cc9ef2d1f
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
<<<<<<< HEAD
        bottomNavigationView = findViewById(R.id.bottom_navigation);
=======
        bottomNavigationView = findViewById(R.id.bottonnav);
>>>>>>> ef3c6549b89830f91825af27b024f24cc9ef2d1f
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
<<<<<<< HEAD
         fab_msg = findViewById(R.id.fab_msg);
        fab_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MassageActivity.class);
                startActivity(i);
            }
        });
=======
>>>>>>> ef3c6549b89830f91825af27b024f24cc9ef2d1f


    }

    private void showHomeFragment() {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,new HomeFragment());
        transaction.commit();
    }
}