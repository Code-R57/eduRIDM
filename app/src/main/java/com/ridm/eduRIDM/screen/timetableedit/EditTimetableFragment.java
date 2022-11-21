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
import com.ridm.eduRIDM.databinding.FragmentEditTimetableBinding;
import com.ridm.eduRIDM.databinding.FragmentPlannerBinding;
import com.ridm.eduRIDM.screen.planner.PlannerViewModel;

public class EditTimetableFragment extends Fragment {

    EditTimetableViewModel viewModel;
    FragmentEditTimetableBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(EditTimetableViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_timetable, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getOnSubmit().observe(getViewLifecycleOwner(), onSubmit -> {
            if(onSubmit == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_editTimetableFragment_to_profileScreenFragment);
                viewModel.doneReachingProfile();
            }
        });

        return binding.getRoot();
    }
}
