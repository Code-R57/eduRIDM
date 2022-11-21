package com.ridm.eduRIDM.screen.planner;

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
import com.ridm.eduRIDM.databinding.FragmentPlannerBinding;

public class PlannerFragment extends Fragment {

    PlannerViewModel viewModel;
    FragmentPlannerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(PlannerViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planner, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToAddPlan().observe(getViewLifecycleOwner(), navigateToAddPlan -> {
            if(navigateToAddPlan == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_plannerFragment_to_addPlanFragment);
                viewModel.doneNavigatingToAddPlan();
            }
        });

        return binding.getRoot();
    }
}
