package com.ridm.eduRIDM.screen.myprofile;

import static com.ridm.eduRIDM.MainActivity.userInfo;

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
import com.ridm.eduRIDM.databinding.FragmentProfileScreenBinding;
import com.ridm.eduRIDM.screen.homescreen.UpcomingClassesListAdapter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfileScreenFragment extends Fragment {
    ProfileViewModel viewModel;
    FragmentProfileScreenBinding binding;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    private DecimalFormat decimalFormat;

    private final String hashDay = "_______";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        decimalFormat = new DecimalFormat("0.000");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        binding.profileName.setText(userInfo.getString("Name"));
        binding.cgpaDetail.setText(String.valueOf(decimalFormat.format(userInfo.get("CGPA"))));
        binding.profileEmail.setText(userInfo.getString("EmailID"));
        binding.semesterDetail.setText(userInfo.getString("CurrentSemester"));

        viewModel.getNavigateToUpdateCGPA().observe(getViewLifecycleOwner(), navigateToUpdateCGPA -> {
            if(navigateToUpdateCGPA == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_profileScreenFragment_to_updateCgpaFragment);
                viewModel.doneNavigatingToUpdateCGPA();
            }
        });

        viewModel.getNavigateToEditTT().observe(getViewLifecycleOwner(), navigateToEditTT -> {
            if(navigateToEditTT == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_profileScreenFragment_to_editTimetableFragment);
                viewModel.doneNavigatingToEditTT();
            }
        });

        viewModel.getNavigateToAddExtraClass().observe(getViewLifecycleOwner(), navigateToAddExtraClass -> {
            if(navigateToAddExtraClass == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_profileScreenFragment_to_addExtraclassFragment);
                viewModel.doneNavigatingToAddExtraClass();
            }
        });

        binding.dateSelector.setOnClickListener(new View.OnClickListener() {
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
                                binding.dateSelector.setText(day + "/" + (month + 1) + "/" + year);

                                String date = year + "-" + (month + 1) + "-" + day;

                                viewModel.setDate(date);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        viewModel.getDate().observe(getViewLifecycleOwner(), date -> {
            try {
                Date reqdDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(reqdDate);

                int day = cal.get(Calendar.DAY_OF_WEEK) - 2;

                if (day == -1) {
                    day = 6;
                }

                String requiredHashDay = hashDay.substring(0, day) + '1' + hashDay.substring(day + 1);

                viewModel.getClassesByDay(requiredHashDay);

                UpcomingClassesListAdapter upcomingClassesAdapter = new UpcomingClassesListAdapter(requireContext(), viewModel.classList, date);

                binding.classesList.setAdapter(upcomingClassesAdapter);
                binding.classesList.setLayoutManager(new LinearLayoutManager(getContext()));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        return binding.getRoot();
    }
}
