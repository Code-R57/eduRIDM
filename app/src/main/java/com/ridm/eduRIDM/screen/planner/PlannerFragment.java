package com.ridm.eduRIDM.screen.planner;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentPlannerBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlannerFragment extends Fragment {

    PlannerViewModel viewModel;
    FragmentPlannerBinding binding;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    String today;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PlannerViewModel.class);
        Date today = new Date();
        this.today = new SimpleDateFormat("yyyy-MM-dd").format(today);

        viewModel.getAllPlans(this.today);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planner, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToAddPlan().observe(getViewLifecycleOwner(), navigateToAddPlan -> {
            if (navigateToAddPlan == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_plannerFragment_to_addPlanFragment);
                viewModel.doneNavigatingToAddPlan();
            }
        });

        PlanCardAdapter adapter = new PlanCardAdapter(requireContext(), viewModel.planList, this.today, this.today);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.plannerDateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                binding.plannerDateSelector.setText(day + "/" + (month + 1) + "/" + year);
                                String date = year + "-" + (month + 1) + "-" + day;
                                viewModel.getAllPlans(date);

                                PlanCardAdapter adapter = new PlanCardAdapter(requireContext(), viewModel.planList, today, date);
                                binding.recyclerView.setAdapter(adapter);
                                binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        return binding.getRoot();
    }
}
