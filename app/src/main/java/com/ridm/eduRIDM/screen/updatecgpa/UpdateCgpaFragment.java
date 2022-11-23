package com.ridm.eduRIDM.screen.updatecgpa;

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

import java.util.ArrayList;
import java.util.List;

public class UpdateCgpaFragment extends Fragment {
    UpdateCgpaViewModel viewModel;
    FragmentUpdateCgpaBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(UpdateCgpaViewModel.class);

        viewModel.getAllCurrentGrades();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_cgpa, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        CgpaListAdapter adapter = new CgpaListAdapter(viewModel.currentGradeList, requireContext());

        binding.gradeList.setAdapter(adapter);

        viewModel.getNavigateToProfile().observe(getViewLifecycleOwner(), navigateToProfile -> {
            if(navigateToProfile == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_updateCgpaFragment_to_profileScreenFragment);
                viewModel.doneNavigatingToProfile();
            }
        });

        binding.updateCgpaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = binding.gradeList.getAdapter().getCount();

                CurrentGrade currentGrade = new CurrentGrade();

                List<CurrentGrade> currentGradeList = new ArrayList<>();

                for(int i=0; i<count; i++) {
                    currentGrade = (CurrentGrade) binding.gradeList.getAdapter().getItem(i);

                    currentGrade.setCourseName(currentGrade.getCourseName());
                    currentGrade.setCourseCode(currentGrade.getCourseCode());
                    currentGrade.setDeptCode(currentGrade.getDeptCode());
                    currentGrade.setGrade(currentGrade.getGrade());

                    currentGradeList.add(currentGrade);
                }

                viewModel.currentGradeList = currentGradeList;

                viewModel.onNavigateToProfileClicked();
            }
        });

        return binding.getRoot();
    }
}
