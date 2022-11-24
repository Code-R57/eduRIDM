package com.ridm.eduRIDM.screen.onboarding;

import static com.ridm.eduRIDM.MainActivity.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

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

        binding.fresherCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    binding.creditsCompleted.setText("0");
                    binding.currentCgpa.setText("0");
                }
                else {
                    binding.creditsCompleted.setText("");
                    binding.currentCgpa.setText("");
                }
            }
        });

        binding.nextButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.userInfo.put("CGPA", Float.parseFloat(String.valueOf(binding.currentCgpa.getText())));
                viewModel.userInfo.put("CollegeID", binding.idEditText.getText().toString());
                viewModel.userInfo.put("CredsCompleted", Integer.parseInt(binding.creditsCompleted.getText().toString()));
                viewModel.userInfo.put("CurrentSemester", binding.currentSemSpinner.getSelectedItem().toString());
                viewModel.userInfo.put("EmailID", account.getEmail());
                viewModel.userInfo.put("Name", binding.nameEditText.getText().toString());

                viewModel.addUserToFirebase(account.getEmail());
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
