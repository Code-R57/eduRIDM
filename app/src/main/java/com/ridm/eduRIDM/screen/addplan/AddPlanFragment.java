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
import com.ridm.eduRIDM.model.room.Plan.Plan;

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

        binding.buttonSubmitPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plan plan = new Plan();

                plan.setTitle(binding.editTextName.getText().toString());
                plan.setDescription(binding.editTextDesc.getText().toString());
                plan.setDate(binding.spinnerDate.getSelectedItem().toString());
                plan.setStartTime(binding.spinnerStartTime.getSelectedItem().toString());
                plan.setEndTime(binding.spinnerEndTime.getSelectedItem().toString());
                plan.setRepeatFor(binding.spinnerRepeatFor.getSelectedItem().toString());
                plan.setRepeatOn(getRepeatOn(binding));
                plan.setRepeat(binding.checkBoxRepeatPlan.isChecked());
                plan.setPriority((Integer) binding.spinnerPriority.getSelectedItem());

                viewModel.plan = plan;

                viewModel.onSubmit();
            }
        });

        return binding.getRoot();
    }

    private String getRepeatOn(FragmentAddPlanBinding binding) {
        StringBuilder repeatOn = new StringBuilder("");

        if(binding.checkBoxMon.isChecked()) {
            repeatOn.append('1');
        }
        else {
            repeatOn.append('0');
        }

        if(binding.checkBoxTue.isChecked()) {
            repeatOn.append('1');
        }
        else {
            repeatOn.append('0');
        }

        if(binding.checkBoxWed.isChecked()) {
            repeatOn.append('1');
        }
        else {
            repeatOn.append('0');
        }

        if(binding.checkBoxThur.isChecked()) {
            repeatOn.append('1');
        }
        else {
            repeatOn.append('0');
        }

        if(binding.checkBoxFri.isChecked()) {
            repeatOn.append('1');
        }
        else {
            repeatOn.append('0');
        }

        if(binding.checkBoxSat.isChecked()) {
            repeatOn.append('1');
        }
        else {
            repeatOn.append('0');
        }

        if(binding.checkBoxSun.isChecked()) {
            repeatOn.append('1');
        }
        else {
            repeatOn.append('0');
        }

        return repeatOn.toString();
    }
}
