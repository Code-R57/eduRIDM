package com.ridm.eduRIDM.screen.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentHomeScreenBinding;
import com.ridm.eduRIDM.databinding.FragmentProfileScreenBinding;
import com.ridm.eduRIDM.screen.myprofile.ProfileViewModel;

public class HomeScreenFragment extends Fragment {
    HomeScreenViewModel viewModel;
    FragmentHomeScreenBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeScreenViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToStopwatchScreen().observe(getViewLifecycleOwner(), navigateToStopwatchScreen -> {
            if(navigateToStopwatchScreen == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_homeScreenFragment_to_stopwatchScreenFragment);
                viewModel.doneNavigatingToStopwatchScreen();
            }
        });

        return binding.getRoot();
    }
}
