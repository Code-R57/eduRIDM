package com.ridm.eduRIDM.screen.timetableedit;

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
import com.ridm.eduRIDM.databinding.FragmentAddTimetableBinding;
import com.ridm.eduRIDM.databinding.FragmentEditTimetableBinding;
import com.ridm.eduRIDM.databinding.FragmentPlannerBinding;
import com.ridm.eduRIDM.screen.planner.PlannerViewModel;

public class AddTimetableFragment extends Fragment {

    AddTimetableViewModel viewModel;
    FragmentAddTimetableBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AddTimetableViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_timetable, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToHomeScreen().observe(getViewLifecycleOwner(), navigateToHomeScreen -> {
            if(navigateToHomeScreen == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addTimetableFragment_to_homeScreenFragment);
                viewModel.doneReachingHomeScreen();
            }
        });

        return binding.getRoot();
    }
}
