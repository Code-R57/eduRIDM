package com.ridm.eduRIDM.screen.stopwatchscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentStopwatchScreenBinding;
import com.ridm.eduRIDM.databinding.FragmentUpdateCgpaBinding;
import com.ridm.eduRIDM.screen.updatecgpa.UpdateCGPAViewModel;

public class StopwatchScreenFragment extends Fragment {
    StopwatchScreenViewModel viewModel;
    FragmentStopwatchScreenBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(StopwatchScreenViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stopwatch_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToHomePage().observe(getViewLifecycleOwner(), navigateToHomePage -> {
            if(navigateToHomePage == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_stopwatchScreenFragment_to_homeScreenFragment);
                viewModel.doneNavigatingToHomePage();
            }
        });

        return binding.getRoot();
    }
}
