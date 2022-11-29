package com.ridm.eduRIDM.screen.homescreen;

import static com.ridm.eduRIDM.MainActivity.userInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

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
    private final String hashDay = "_______";

    HomeScreenViewModel viewModel;
    FragmentHomeScreenBinding binding;

    private String today;
    private String tomorrow;
    private String dayAfterTomorrow;
    private String requiredHashToday, requiredHashTomorrow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(HomeScreenViewModel.class);

        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        Date dayAfterTomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24 * 2));
        this.today = new SimpleDateFormat("yyyy-MM-dd").format(today);
        this.tomorrow = new SimpleDateFormat("yyyy-MM-dd").format(tomorrow);
        this.dayAfterTomorrow = new SimpleDateFormat("yyyy-MM-dd").format(dayAfterTomorrow);
        Calendar calender = Calendar.getInstance();
        int day = calender.get(Calendar.DAY_OF_WEEK) - 2;

        if (day == -1) {
            day = 6;
        }
        requiredHashToday = hashDay.substring(0, day) + '1' + hashDay.substring(day + 1);
        requiredHashTomorrow = hashDay.substring(0, (day + 1) % 7) + '1' + hashDay.substring((day + 1) % 7 + 1);
        viewModel.getUpcomingEvals(this.today, this.dayAfterTomorrow);
        viewModel.getClassesByDay(requiredHashToday);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false);
        getActivity().setTitle("eduRIDM");

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        if(userInfo != null)
            binding.nameText.setText(userInfo.getString("Name").split(" ")[0]);

        UpcomingClassesListAdapter upcomingClassesAdapter = new UpcomingClassesListAdapter(requireContext(), viewModel.classList, today);

        binding.yourClassesList.setAdapter(upcomingClassesAdapter);
        binding.yourClassesList.setLayoutManager(new LinearLayoutManager(getContext()));

        UpcomingEvalsAdapter adapter = new UpcomingEvalsAdapter((ArrayList<Eval>) viewModel.upcomingEvalList, requireContext());
        binding.upcomingEvalsList.setAdapter(adapter);

        binding.daySelector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (binding.daySelector.getCheckedRadioButtonId() == R.id.today) {
                    viewModel.setCurrentSelection("Today");
                    viewModel.getClassesByDay(requiredHashToday);

                    UpcomingClassesListAdapter upcomingClassesAdapter = new UpcomingClassesListAdapter(requireContext(), viewModel.classList, today);

                    binding.yourClassesList.setAdapter(upcomingClassesAdapter);
                    binding.yourClassesList.setLayoutManager(new LinearLayoutManager(getContext()));
                } else {
                    viewModel.setCurrentSelection("Tomorrow");
                    viewModel.getClassesByDay(requiredHashTomorrow);

                    UpcomingClassesListAdapter upcomingClassesAdapter = new UpcomingClassesListAdapter(requireContext(), viewModel.classList, tomorrow);

                    binding.yourClassesList.setAdapter(upcomingClassesAdapter);
                    binding.yourClassesList.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }
        });

        viewModel.getNavigateToStopwatchScreen().observe(getViewLifecycleOwner(), navigateToStopwatchScreen -> {
            if (navigateToStopwatchScreen == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_homeScreenFragment_to_stopwatchScreenFragment);
                viewModel.doneNavigatingToStopwatchScreen();
            }
        });

        return binding.getRoot();
    }
}
