package com.ridm.eduRIDM.screen.addevaluative;

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
import com.ridm.eduRIDM.databinding.FragmentAddEvaluativeBinding;

public class AddEvaluativeFragment extends Fragment {

    AddEvaluativeViewModel viewModel;
    FragmentAddEvaluativeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(AddEvaluativeViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_evaluative, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToMyAcads().observe(getViewLifecycleOwner(), navigateToMyAcads -> {
            if (navigateToMyAcads == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_addEvaluativeFragment_to_myAcadsFragment);
                viewModel.doneNavigatingToMyAcads();
            }
        });

        return binding.getRoot();
    }
}
