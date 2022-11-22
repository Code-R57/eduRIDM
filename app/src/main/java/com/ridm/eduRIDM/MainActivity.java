package com.ridm.eduRIDM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.ridm.eduRIDM.databinding.ActivityMainBinding;
import com.ridm.eduRIDM.model.room.RoomRepository;

public class MainActivity extends AppCompatActivity {

    public static RoomRepository roomRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        roomRepository = new RoomRepository(getApplication());

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
    }
}