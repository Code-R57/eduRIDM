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

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ridm.eduRIDM.databinding.ActivityMainBinding;
import com.ridm.eduRIDM.model.room.RoomRepository;

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
//        NavigationUI.setupActionBarWithNavController(this, navController);

        toolbar = findViewById(R.id.mtoolbar);
        setSupportActionBar(toolbar);
        NavigationUI.setupActionBarWithNavController(this,navController);

        bottomView = findViewById(R.id.bottomNav);
        NavigationUI.setupWithNavController(bottomView, navController);
        NavigationUI.setupWithNavController(toolbar,navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if(navDestination.getId() == R.id.homeScreenFragment || navDestination.getId() == R.id.plannerFragment || navDestination.getId() == R.id.myAcadsFragment) {
                    bottomView.setVisibility(View.VISIBLE);
                }
                else {
                    bottomView.setVisibility(View.GONE);
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
        switch(item.getItemId()) {
            case R.id.settingsFragment:

        }
        return super.onOptionsItemSelected(item);
    }
}