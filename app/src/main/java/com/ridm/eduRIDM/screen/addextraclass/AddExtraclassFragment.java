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
import com.ridm.eduRIDM.screen.addplan.AddPlanViewModel;

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

        return binding.getRoot();
    }
}
