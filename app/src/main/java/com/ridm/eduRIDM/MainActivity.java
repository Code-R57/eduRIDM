package com.ridm.eduRIDM;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ridm.eduRIDM.databinding.ActivityMainBinding;
import com.ridm.eduRIDM.model.room.RoomRepository;
import com.ridm.eduRIDM.screen.myprofile.ProfileScreenFragment;
import com.ridm.eduRIDM.screen.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    public BottomNavigationView bottomView;
    public Toolbar toolbar;
    NavController navController;
    public static RoomRepository roomRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        roomRepository = new RoomRepository(getApplication());

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        toolbar = findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);

        NavigationUI.setupActionBarWithNavController(this, navController);

        bottomView = findViewById(R.id.bottomNav);
        NavigationUI.setupWithNavController(bottomView, navController);
        NavigationUI.setupWithNavController(toolbar, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.homeScreenFragment || navDestination.getId() == R.id.plannerFragment || navDestination.getId() == R.id.myAcadsFragment) {
                    bottomView.setVisibility(View.VISIBLE);
                } else {
                    bottomView.setVisibility(View.GONE);
                }
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.welcomeScreenFragment) {
                    toolbar.setVisibility(View.GONE);
                } else if (navDestination.getId() == R.id.stopwatchScreenFragment || navDestination.getId() == R.id.registerFragment || navDestination.getId() == R.id.addTimetableFragment) {
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.app_primary)));
                    toolbar.setNavigationIcon(null);
                } else if (navDestination.getId() == R.id.homeScreenFragment || navDestination.getId() == R.id.plannerFragment || navDestination.getId() == R.id.myAcadsFragment) {
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                    toolbar.setNavigationIcon(null);
                } else {
                    toolbar.setVisibility(View.VISIBLE);
                    toolbar.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        switch (item.getItemId()) {
            case R.id.settingsFragment:
                navController.navigate(R.id.settingsFragment);
                return true;
            case R.id.profileScreenFragment:
                navController.navigate(R.id.profileScreenFragment);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.stopwatchScreenFragment || navDestination.getId() == R.id.registerFragment || navDestination.getId() == R.id.addTimetableFragment) {
                    MenuItem item1 = menu.findItem(R.id.settingsFragment);
                    if (item1 != null)
                        item1.setVisible(false);

                    MenuItem item2 = menu.findItem(R.id.profileScreenFragment);
                    if (item2 != null)
                        item2.setVisible(false);
                } else if (navDestination.getId() == R.id.profileScreenFragment) {
                    MenuItem item2 = menu.findItem(R.id.profileScreenFragment);
                    if (item2 != null)
                        item2.setVisible(false);
                } else if (navDestination.getId() == R.id.settingsFragment) {
                    MenuItem item1 = menu.findItem(R.id.settingsFragment);
                    if (item1 != null)
                        item1.setVisible(false);
                }
                else
                {
                    MenuItem item1 = menu.findItem(R.id.settingsFragment);
                    if (item1 != null)
                        item1.setVisible(true);

                    MenuItem item2 = menu.findItem(R.id.profileScreenFragment);
                    if (item2 != null)
                        item2.setVisible(true);
                }
            }
        });
        return true;
    }
}