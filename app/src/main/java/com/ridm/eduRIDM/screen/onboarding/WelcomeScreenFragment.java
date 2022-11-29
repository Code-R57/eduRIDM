package com.ridm.eduRIDM.screen.onboarding;

import static com.ridm.eduRIDM.MainActivity.account;

import android.content.Context;
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
import com.ridm.eduRIDM.databinding.FragmentWelcomeScreenBinding;

public class WelcomeScreenFragment extends Fragment {

    WelcomeScreenViewModel viewModel;
    FragmentWelcomeScreenBinding binding;
    private Listener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(WelcomeScreenViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        if(account != null) {
            binding.googleSignInButton.setVisibility(View.GONE);
        }

        viewModel.getNavigateToRegister().observe(getViewLifecycleOwner(), navigateToRegister -> {
            if (navigateToRegister == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_welcomeScreenFragment_to_registerFragment);
                viewModel.doneNavigatingToRegister();
            }
        });

        binding.googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.signInClicked();
            }
        });

        return binding.getRoot();
    }

    public interface Listener {
        void signInClicked();
    }
}
