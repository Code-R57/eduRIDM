package com.ridm.eduRIDM.screen.addextraclass;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentAddExtraclassBinding;
import com.ridm.eduRIDM.model.room.ExtraClass.ExtraClass;

import java.util.Calendar;

public class AddExtraclassFragment extends Fragment {

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    AddExtraclassViewModel viewModel;
    FragmentAddExtraclassBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(AddExtraclassViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_extraclass, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToProfile().observe(getViewLifecycleOwner(), navigateToProfile -> {
            if (navigateToProfile == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addExtraclassFragment_to_profileScreenFragment);
                viewModel.doneNavigatingToProfile();
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtraClass extraClass = new ExtraClass();

                String[] course = binding.subjectSpinner.getSelectedItem().toString().split(" ");

                extraClass.setDeptCode(course[0]);
                extraClass.setCourseCode(course[1]);
                extraClass.setSection(course[2]);
                extraClass.setDate(binding.datePicker.getText().toString());
                extraClass.setTime(binding.startTimePicker.getText().toString());
                extraClass.setTime(binding.endTimePicker.getText().toString());
                viewModel.extraClass = extraClass;

                viewModel.onSubmit();
            }
        });
        binding.datePicker.setOnClickListener(new View.OnClickListener() {
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
                                binding.datePicker.setText(day + "-" + (month + 1) + "-" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        binding.startTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "";
                        } else {
                            amPm = "";
                        }
                        binding.startTimePicker.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });

        binding.endTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            amPm = "";
                        } else {
                            amPm = "";
                        }
                        binding.endTimePicker.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });

        return binding.getRoot();
    }
}
