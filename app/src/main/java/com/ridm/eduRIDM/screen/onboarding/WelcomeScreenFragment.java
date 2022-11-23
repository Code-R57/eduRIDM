package com.ridm.eduRIDM.screen.onboarding;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.MainActivity;
import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentWelcomeScreenBinding;

public class WelcomeScreenFragment extends Fragment {

    WelcomeScreenViewModel viewModel;
    FragmentWelcomeScreenBinding binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(WelcomeScreenViewModel.class);

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_welcome_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        viewModel.getNavigateToRegister().observe(getViewLifecycleOwner(), navigateToRegister -> {
            if(navigateToRegister == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_welcomeScreenFragment_to_registerFragment);
                viewModel.doneNavigatingToRegister();
            }
        });

        binding.googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent, MainActivity.RC_SIGN_IN);
            }
        });

        return binding.getRoot();
    }
}
