package com.ridm.eduRIDM.screen.updatecgpa;

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
import com.ridm.eduRIDM.databinding.FragmentUpdateCgpaBinding;
import com.ridm.eduRIDM.screen.myprofile.ProfileViewModel;

public class UpdateCgpaFragment extends Fragment {
    UpdateCGPAViewModel viewModel;
    FragmentUpdateCgpaBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(UpdateCGPAViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_cgpa, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToProfile().observe(getViewLifecycleOwner(), navigateToProfile -> {
            if(navigateToProfile == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_updateCgpaFragment_to_profileScreenFragment);
                viewModel.doneNavigatingToProfile();
            }
        });

        return binding.getRoot();
    }
}
