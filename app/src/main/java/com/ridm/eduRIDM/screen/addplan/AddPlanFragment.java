package com.ridm.eduRIDM.screen.addplan;

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
import com.ridm.eduRIDM.databinding.FragmentAddPlanBinding;

public class AddPlanFragment extends Fragment {

    AddPlanViewModel viewModel;
    FragmentAddPlanBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(AddPlanViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_plan, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToPlanner().observe(getViewLifecycleOwner(), navigateToPlanner -> {
            if(navigateToPlanner == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addPlanFragment_to_plannerFragment);
                viewModel.doneNavigatingToPlanner();
            }
        });

        return binding.getRoot();
    }
}
