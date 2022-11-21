package com.ridm.eduRIDM.screen.myprofile;

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
import com.ridm.eduRIDM.databinding.FragmentProfileScreenBinding;
import com.ridm.eduRIDM.databinding.FragmentRegisterBinding;
import com.ridm.eduRIDM.screen.onboarding.RegisterViewModel;

public class ProfileScreenFragment extends Fragment {
    ProfileViewModel viewModel;
    FragmentProfileScreenBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToUpdateCGPA().observe(getViewLifecycleOwner(), navigateToUpdateCGPA -> {
            if(navigateToUpdateCGPA == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_profileScreenFragment_to_updateCgpaFragment);
                viewModel.doneNavigatingToUpdateCGPA();
            }
        });

        return binding.getRoot();
    }
}
