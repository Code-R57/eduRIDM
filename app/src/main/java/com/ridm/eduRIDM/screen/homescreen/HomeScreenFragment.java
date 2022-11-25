package com.ridm.eduRIDM.screen.homescreen;

import android.os.Bundle;
import android.util.Log;
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

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentHomeScreenBinding;
import com.ridm.eduRIDM.model.room.Backlog.Backlog;
import com.ridm.eduRIDM.model.room.Eval.Eval;
import com.ridm.eduRIDM.model.room.TimeTable.TimeTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeScreenFragment extends Fragment {
    HomeScreenViewModel viewModel;
    FragmentHomeScreenBinding binding;
    private String date1;
    private String requiredHashToday, requiredHashTomorrow;
    private Date today;
    private Date dayAfterTomorrow;
    private int day;
    private final String hashDay = "%%%%%%%";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(HomeScreenViewModel.class);

        Date today = new Date();
        date1 = new SimpleDateFormat("yyyy-MM-dd").format(today);
        Date dayAfterTomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 2));
        String date2 = new SimpleDateFormat("yyyy-MM-dd").format(dayAfterTomorrow);
        Calendar calender = Calendar.getInstance();
        int day = calender.get(Calendar.DAY_OF_WEEK);
        String requiredHashToday = hashDay.substring(0, day) + '1' + hashDay.substring(day + 1);
        String requiredHashTomorrow = hashDay.substring(0, (day + 1) % 7) + '1' + hashDay.substring((day + 2) % 7);

        viewModel.getUpcomingEvals(date1, date2);
        viewModel.getClassesByDay(requiredHashToday);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        UpcomingEvalsAdapter adapter = new UpcomingEvalsAdapter((ArrayList<Eval>) viewModel.upcomingEvalList, requireContext());
        binding.upcomingEvalsList.setAdapter(adapter);

        UpcomingClassesListAdapter upcomingClassesAdapter = new UpcomingClassesListAdapter(requireContext(), viewModel.classList, viewModel, date1);

        binding.yourClassesList.setAdapter(upcomingClassesAdapter);
        binding.yourClassesList.setLayoutManager(new LinearLayoutManager(getContext()));

        binding.daySelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (binding.daySelector.getCheckedRadioButtonId() == R.id.today) {
                    viewModel.setCurrentSelection("Today");
                    viewModel.getClassesByDay(requiredHashToday);
                    upcomingClassesAdapter.notifyDataSetChanged();
                }
                else {
                    viewModel.setCurrentSelection("Tomorrow");
                    viewModel.getClassesByDay(requiredHashTomorrow);
                    upcomingClassesAdapter.notifyDataSetChanged();
                }
            }
        });



        viewModel.getNavigateToStopwatchScreen().observe(getViewLifecycleOwner(),navigateToStopwatchScreen -> {
            if (navigateToStopwatchScreen == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_homeScreenFragment_to_stopwatchScreenFragment);
                viewModel.doneNavigatingToStopwatchScreen();
            }
        });

        return binding.getRoot();
    }
}
