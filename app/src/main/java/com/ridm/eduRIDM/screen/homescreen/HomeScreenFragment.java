package com.ridm.eduRIDM.screen.homescreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentHomeScreenBinding;
import com.ridm.eduRIDM.model.room.Eval.Eval;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeScreenFragment extends Fragment {
    HomeScreenViewModel viewModel;
    FragmentHomeScreenBinding binding;
    private Date today;
    private Date dayAfterTomorrow;
    private int day;
    private String hashDay = "%%%%%%%";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(HomeScreenViewModel.class);

        today = new Date();
        String date1 = new SimpleDateFormat("yyyy-MM-dd").format(today);
        dayAfterTomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 2));
        String date2 = new SimpleDateFormat("yyyy-MM-dd").format(dayAfterTomorrow);
        Calendar calender = Calendar.getInstance();
        day = calender.get(Calendar.DAY_OF_WEEK);
        hashDay = hashDay.substring(0, day) + '1' + hashDay.substring(day + 1);

        viewModel.getAllEvals();
        viewModel.getClassesToday(hashDay);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        UpcomingEvalsAdapter adapter = new UpcomingEvalsAdapter((ArrayList<Eval>) viewModel.upcomingEvalList, requireContext());

        binding.upcomingEvalsList.setAdapter(adapter);

        UpcomingClassesListAdapter upcomingClassesAdapter = new UpcomingClassesListAdapter(requireContext(), viewModel.classList);

        binding.yourClassesList.setAdapter(upcomingClassesAdapter);
        binding.yourClassesList.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getNavigateToStopwatchScreen().observe(getViewLifecycleOwner(), navigateToStopwatchScreen -> {
            if (navigateToStopwatchScreen == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_homeScreenFragment_to_stopwatchScreenFragment);
                viewModel.doneNavigatingToStopwatchScreen();
            }
        });

        return binding.getRoot();
    }
}
