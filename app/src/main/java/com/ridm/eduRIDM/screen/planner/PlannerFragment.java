package com.ridm.eduRIDM.screen.planner;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentPlannerBinding;
import com.ridm.eduRIDM.model.room.Plan.Plan;
import com.ridm.eduRIDM.screen.addplan.AddPlanViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PlannerFragment extends Fragment {

    PlannerViewModel viewModel;
    FragmentPlannerBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PlannerViewModel.class);
        Date today = new Date();
        String date = new SimpleDateFormat("yyyy-mm-dd").format(today);

        viewModel.getAllPlans(date);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_planner, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        PlanCardAdapter adapter = new PlanCardAdapter(requireContext(), (ArrayList<Plan>) viewModel.planList);

        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = new ViewModelProvider(this).get(PlannerViewModel.class);
        viewModel.getNavigateToAddPlan().observe(getViewLifecycleOwner(), navigateToAddPlan -> {
            if (navigateToAddPlan == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_plannerFragment_to_addPlanFragment);
                viewModel.doneNavigatingToAddPlan();
            }
        });

        return binding.getRoot();
    }
}
