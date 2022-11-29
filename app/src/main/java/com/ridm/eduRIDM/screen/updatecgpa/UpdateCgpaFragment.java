package com.ridm.eduRIDM.screen.updatecgpa;

import static com.ridm.eduRIDM.MainActivity.userInfo;

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
import com.ridm.eduRIDM.databinding.FragmentUpdateCgpaBinding;
import com.ridm.eduRIDM.model.room.CurrentGrade.CurrentGrade;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UpdateCgpaFragment extends Fragment {
    UpdateCgpaViewModel viewModel;
    FragmentUpdateCgpaBinding binding;

    private DecimalFormat decimalFormat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(UpdateCgpaViewModel.class);

        viewModel.getAllCurrentGrades();

        decimalFormat = new DecimalFormat("0.000");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_cgpa, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        CgpaListAdapter adapter = new CgpaListAdapter(viewModel.currentGradeList, requireContext());

        binding.gradeList.setAdapter(adapter);

        viewModel.calculateSG();

        viewModel.getNavigateToProfile().observe(getViewLifecycleOwner(), navigateToProfile -> {
            if (navigateToProfile == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_updateCgpaFragment_to_profileScreenFragment);
                viewModel.doneNavigatingToProfile();
            }
        });

        binding.currCgpaValue.setText(String.valueOf(decimalFormat.format(userInfo.get("CGPA"))));
        binding.finalCgpaValue.setText(String.valueOf(decimalFormat.format(userInfo.get("CGPA"))));

        if(String.valueOf(viewModel.currentSG).equals("NaN")) {
            viewModel.currentSG = 0;
        }
        binding.sgpaValue.setText(String.valueOf(viewModel.currentSG));

        binding.updateCgpaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = binding.gradeList.getAdapter().getCount();

                CurrentGrade currentGrade = new CurrentGrade();

                long totalCreds = (long) userInfo.get("CredsCompleted");
                int semCreds = 0;

                float SGPA = 0;
                float CGPA = Float.parseFloat(binding.currCgpaValue.getText().toString());

                List<CurrentGrade> currentGradeList = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    currentGrade = (CurrentGrade) binding.gradeList.getAdapter().getItem(i);

                    SGPA += currentGrade.getCredits() * viewModel.getGradePoint(currentGrade.getGrade());

                    if (viewModel.getGradePoint(currentGrade.getGrade()) != 0)
                        semCreds += currentGrade.getCredits();

                    currentGradeList.add(currentGrade);
                }

                CGPA = (SGPA + totalCreds * CGPA) / (float) (totalCreds + semCreds);
                SGPA = SGPA / (float) semCreds;

                viewModel.currentGradeList = currentGradeList;

                if (binding.updateCgpaButton.getText() == "Done")
                    viewModel.onNavigateToProfileClicked();
                else {
                    SGPA = Float.parseFloat(decimalFormat.format(SGPA));
                    binding.sgpaValue.setText(String.valueOf(SGPA));

                    CGPA = Float.parseFloat(decimalFormat.format(CGPA));
                    binding.finalCgpaValue.setText(String.valueOf(CGPA));

                    String submitText = "Done";
                    binding.updateCgpaButton.setText(submitText);
                }
            }
        });

        return binding.getRoot();
    }
}
