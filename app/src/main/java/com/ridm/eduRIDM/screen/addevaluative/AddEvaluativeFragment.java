package com.ridm.eduRIDM.screen.addevaluative;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.AppBroadcastReceiver;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentAddEvaluativeBinding;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.TimeTable.DistinctClasses;

import java.util.Calendar;
import java.util.Date;

public class AddEvaluativeFragment extends Fragment {

    AddEvaluativeViewModel viewModel;
    FragmentAddEvaluativeBinding binding;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(AddEvaluativeViewModel.class);

        viewModel.getMyCourses();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_evaluative, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        ArrayAdapter<DistinctClasses> coursesAdapter = new ArrayAdapter<DistinctClasses>(requireActivity(), android.R.layout.simple_spinner_item, viewModel.courseList);

        binding.spinnerSubject.setAdapter(coursesAdapter);

        viewModel.getNavigateToMyAcads().observe(getViewLifecycleOwner(), navigateToMyAcads -> {
            if (navigateToMyAcads == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addEvaluativeFragment_to_myAcadsFragment);
                viewModel.doneNavigatingToMyAcads();
            }
        });

        binding.addEvalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Eval eval = new Eval();

                eval.setCourseName(binding.spinnerSubject.getSelectedItem().toString());

                String[] dates = binding.addEvalDatePicker.getText().toString().split("/");
                String date = dates[2] + "-" + dates[1] + "-" + dates[0];
                eval.setDate(date);

                eval.setTime(binding.addEvalTimePicker.getText().toString());
                eval.setType(binding.spinnerType.getSelectedItem().toString());
                eval.setSyllabus(binding.syllabusText.getText().toString());
                eval.setNature(binding.spinnerNature.getSelectedItem().toString());

                Intent notifyIntent = new Intent(getActivity(), AppBroadcastReceiver.class);
                notifyIntent.putExtra("Notif Desc - Receiver", eval.getCourseName() + " " + eval.getNature() + " tomorrow");
                notifyIntent.putExtra("Notif ID - Receiver", eval.getEvalID());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 13, notifyIntent, PendingIntent.FLAG_MUTABLE);
                AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

                String[] time = binding.addEvalTimePicker.getText().toString().split(":");

                Date today = new Date();
                Date toSet = new Date();
                toSet.setYear(Integer.parseInt(dates[2]) - 1900);
                toSet.setMonth(Integer.parseInt(dates[1]) - 1);
                toSet.setDate(Integer.parseInt(dates[0]));

                long timeDiff = Math.abs(toSet.getTime() - 86400000 - today.getTime());
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeDiff, pendingIntent);

                viewModel.eval = eval;
                viewModel.onNavigateToMyAcadsClicked();
            }
        });

        binding.addEvalDatePicker.setOnClickListener(new View.OnClickListener() {
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
                                String date = day + "/" + (month + 1) + "/" + year;
                                binding.addEvalDatePicker.setText(date);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        binding.addEvalTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm = "";
                        String time = String.format("%02d:%02d", hourOfDay, minutes) + amPm;
                        binding.addEvalTimePicker.setText(time);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
        return binding.getRoot();
    }
}
