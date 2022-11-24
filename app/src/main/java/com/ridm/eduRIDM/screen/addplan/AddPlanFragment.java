package com.ridm.eduRIDM.screen.addplan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentAddPlanBinding;
import com.ridm.eduRIDM.model.room.Plan.Plan;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPlanFragment extends Fragment {

    //final Calendar myCalendar= Calendar.getInstance();
    EditText editText;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

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
            if (navigateToPlanner == Boolean.TRUE) {
                viewModel.doneNavigatingToPlanner();
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addPlanFragment_to_plannerFragment);
            }
        });

        binding.buttonSubmitPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Plan plan = new Plan();
//                mDisplayDate = (EditText) view.findViewById(R.id.date_picker);
                plan.setTitle(binding.editTextName.getText().toString());
                plan.setDescription(binding.editTextDesc.getText().toString());
                plan.setDate(binding.datePicker.getText().toString());
                plan.setStartTime(binding.spinnerStartTime.getText().toString());
                plan.setEndTime(binding.spinnerEndTime.getText().toString());
                plan.setRepeatFor(binding.spinnerRepeatFor.getSelectedItem().toString());
                plan.setRepeatOn(getRepeatOn(binding));
                plan.setRepeat(binding.checkBoxRepeatPlan.isChecked());
                String priorityString = binding.spinnerPriority.getSelectedItem().toString();
                if (priorityString.equals("High"))
                    plan.setPriority(1);
                else
                    plan.setPriority(0);
                viewModel.plan = plan;
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
                                binding.datePicker.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        binding.spinnerStartTime.setOnClickListener(new View.OnClickListener() {
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
                        binding.spinnerStartTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, 0, 0, false);
                timePickerDialog.show();

            }

        });


        binding.spinnerEndTime.setOnClickListener(new View.OnClickListener() {
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
                        binding.spinnerEndTime.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);

                    }
                }, 0, 0, false);
                timePickerDialog.show();

            }

        });

        return binding.getRoot();
    }


    private String getRepeatOn(FragmentAddPlanBinding binding) {
        StringBuilder repeatOn = new StringBuilder("");

        if (binding.checkBoxMon.isChecked()) {
            repeatOn.append('1');
        } else {
            repeatOn.append('0');
        }

        if (binding.checkBoxTue.isChecked()) {
            repeatOn.append('1');
        } else {
            repeatOn.append('0');
        }

        if (binding.checkBoxWed.isChecked()) {
            repeatOn.append('1');
        } else {
            repeatOn.append('0');
        }

        if (binding.checkBoxThur.isChecked()) {
            repeatOn.append('1');
        } else {
            repeatOn.append('0');
        }

        if (binding.checkBoxFri.isChecked()) {
            repeatOn.append('1');
        } else {
            repeatOn.append('0');
        }

        if (binding.checkBoxSat.isChecked()) {
            repeatOn.append('1');
        } else {
            repeatOn.append('0');
        }

        if (binding.checkBoxSun.isChecked()) {
            repeatOn.append('1');
        } else {
            repeatOn.append('0');
        }

        return repeatOn.toString();
    }
}
