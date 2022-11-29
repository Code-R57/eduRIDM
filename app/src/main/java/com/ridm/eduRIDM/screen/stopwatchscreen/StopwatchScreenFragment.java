package com.ridm.eduRIDM.screen.stopwatchscreen;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.ridm.eduRIDM.R;
import com.ridm.eduRIDM.databinding.FragmentStopwatchScreenBinding;

public class StopwatchScreenFragment extends Fragment {

    Chronometer chronometer;
    ImageButton play_button,stop_button;
    private boolean isResume;
    Handler handler;
    long tmilisec,tstart,tbuff,tupdate=0L;
    int sec,min,hours,milisec;
    MediaPlayer mediaPlayer;
    private boolean songPlay=false;
    public int c=0;

    StopwatchScreenViewModel viewModel;
    FragmentStopwatchScreenBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(StopwatchScreenViewModel.class);

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_stopwatch_screen, container, false);

        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);



        viewModel.getNavigateToHomePage().observe(getViewLifecycleOwner(), navigateToHomePage -> {
            if(navigateToHomePage == Boolean.TRUE) {
                Navigation.findNavController(this.requireView()).navigate(R.id.action_stopwatchScreenFragment_to_homeScreenFragment);
                viewModel.doneNavigatingToHomePage();
            }
        });

        handler = new Handler();


        binding.musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!songPlay) {
                    songPlay=true;
                    c++;
                    float deg = binding.musicButton.getRotation() + 180F;
                    binding.musicButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_music_off_24));
                    binding.musicButton.animate().rotation(360f).setDuration(2500).start();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.study_lofi);
                    mediaPlayer.start();
                }
                else
                {
                    songPlay=false;
                    c++;
                    binding.musicButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_music_note_24));
                    binding.musicButton.clearAnimation();
                    binding.musicButton.clearFocus();
                    mediaPlayer.stop();
                }
            }
        });


        binding.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume){
                    tstart=SystemClock.uptimeMillis();
                    handler.postDelayed(runnable,0);
                    binding.timer.start();
                    isResume=true;
                    binding.stopButton.setVisibility(View.GONE);
                    binding.playButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_pause_24));
                }
                else
                {
                    tbuff+=tmilisec;
                    handler.removeCallbacks(runnable);
                    binding.timer.stop();
                    isResume=false;
                    binding.stopButton.setVisibility(View.VISIBLE);
                    binding.playButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24));

                }
            }
        });
        binding.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isResume)
                {
                    binding.playButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_play_arrow_24));
                    tmilisec=0L;
                    tstart=0L;
                    tbuff=0L;
                    tupdate=0L;
                    sec=0;
                    min=0;
                    hours=0;
                    milisec=0;
                    binding.timer.setText("00:00:00");

                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(c>0) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

    }
    public Runnable runnable=new Runnable() {
        @Override
        public void run() {
            tmilisec= SystemClock.uptimeMillis() - tstart;
            tupdate= tbuff + tmilisec;
            sec = (int)(tupdate/1000);
            min = sec/60;
            hours= sec/3600;
            sec= sec%60;
            milisec=(int)(tupdate%100);

            binding.timer.setText((String.format("%02d",hours))+":"+String.format("%02d",min)+":"+String.format("%02d",sec));
            handler.postDelayed(this, 60);

        }
    };
}
