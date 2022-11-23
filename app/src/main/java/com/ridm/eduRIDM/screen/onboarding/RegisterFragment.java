package com.ridm.eduRIDM.screen.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentPlannerBinding;
import com.ridm.eduRIDM.databinding.FragmentRegisterBinding;
import com.ridm.eduRIDM.screen.planner.PlannerViewModel;
import com.ridm.eduRIDM.screen.onboarding.RegisterViewModel;

public class RegisterFragment extends Fragment {

    RegisterViewModel viewModel;
    FragmentRegisterBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToAddTimetable().observe(getViewLifecycleOwner(), navigateToAddTimetable -> {
            if(navigateToAddTimetable == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_registerFragment_to_addTimetableFragment);
                viewModel.doneNavigatingToAddTimetable();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
