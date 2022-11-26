package com.ridm.eduRIDM.screen.myacads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentMyAcadsBinding;

public class MyAcadsFragment extends Fragment {

    MyAcadsViewModel viewModel;
    FragmentMyAcadsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MyAcadsViewModel.class);

        viewModel.getDistinctCourses();
        viewModel.getAllEvals();
        viewModel.getBacklogs();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_acads, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getBacklogs();

        viewModel.getNavigateToAddEval().observe(getViewLifecycleOwner(), navigateToAddEval -> {
            if (navigateToAddEval == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_myAcadsFragment_to_addEvaluativeFragment);
                viewModel.doneNavigatingToAddEval();
            }
        });

        viewModel.getCurrentSelection().observe(getViewLifecycleOwner(), checkedState -> {
            AcadsListAdapter acadsListAdapter = new AcadsListAdapter(requireContext(), viewModel.courses, viewModel.evalList, checkedState, viewModel.courseBacklog);
            binding.acadsList.setAdapter(acadsListAdapter);
            binding.acadsList.setLayoutManager(new LinearLayoutManager(getContext()));
        });

        binding.evalBacklogSelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (binding.evalBacklogSelector.getCheckedRadioButtonId() == R.id.evals) {
                    viewModel.setCurrentSelection("Evals");
                    binding.header.setText(R.string.my_acads_header_eval);
                    binding.addEval.setVisibility(View.VISIBLE);
                } else {
                    viewModel.setCurrentSelection("Backlog");
                    binding.header.setText(R.string.my_acads_header_backlog);
                    binding.addEval.setVisibility(View.GONE);
                }
            }
        });

        return binding.getRoot();
    }
}
