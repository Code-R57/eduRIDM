package com.ridm.eduRIDM.screen.addextraclass;

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
import com.ridm.eduRIDM.databinding.FragmentAddExtraclassBinding;
import com.ridm.eduRIDM.model.room.ExtraClass.ExtraClass;
import com.ridm.eduRIDM.screen.addplan.AddPlanViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddExtraclassFragment extends Fragment {

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
            if(navigateToProfile == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addExtraclassFragment_to_profileScreenFragment);
                viewModel.doneNavigatingToProfile();
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExtraClass extraClass= new ExtraClass();

                String course[]=binding.subjectSpinner.getSelectedItem().toString().split(" ");

                extraClass.setDeptCode(course[0]);
                extraClass.setCourseCode(course[1]);
                extraClass.setSection(course[2]);
                extraClass.setDate(binding.dateSpinner.getSelectedItem().toString());
                extraClass.setTime(binding.startTimeSpinner.getSelectedItem().toString());

                String stime = binding.startTimeSpinner.getSelectedItem().toString();
                String etime = binding.endTimeSpinner.getSelectedItem().toString();


                // Creating a SimpleDateFormat object
                // to parse time in the format HH:MM:SS
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

                // Parsing the Time Period
                Date date1 = null;
                try {
                    date1 = simpleDateFormat.parse(stime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date date2 = null;
                try {
                    date2 = simpleDateFormat.parse(etime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // Calculating the difference in Seconds

                long differenceInSeconds=
                        Math.abs(date2.getTime()- date1.getTime());

                // Calculating the difference in Hours
                long differenceInHours
                        = (differenceInSeconds / (60 * 1000))
                        % 24;

                // Calculating the difference in Minutes
                long differenceInMinutes
                        = (differenceInSeconds / (1000)) % 60;

                extraClass.setDuration((int) differenceInMinutes);

                viewModel.extraClass = extraClass;

                viewModel.onSubmit();
            }
        });

        return binding.getRoot();
    }
}
