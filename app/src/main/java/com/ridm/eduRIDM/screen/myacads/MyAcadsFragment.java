package com.ridm.eduRIDM.screen.myacads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentMyAcadsBinding;
import com.ridm.eduRIDM.screen.planner.PlannerViewModel;

public class MyAcadsFragment extends Fragment {

    MyAcadsViewModel viewModel;
    FragmentMyAcadsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(MyAcadsViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_acads, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToAddEval().observe(getViewLifecycleOwner(), navigateToAddEval -> {
            if(navigateToAddEval == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_myAcadsFragment_to_addEvaluativeFragment);
                viewModel.doneNavigatingToAddEval();
            }
        });

        return binding.getRoot();
    }
}
