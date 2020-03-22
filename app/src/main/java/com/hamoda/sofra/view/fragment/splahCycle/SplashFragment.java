package com.hamoda.sofra.view.fragment.splahCycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hamoda.sofra.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashFragment extends Fragment {

    @BindView(R.id.splash_fragment_im_logo)
    ImageView splashFragmentImLogo;

    private Handler handler;
    private int timeSplash = 2000;
    private NavController navController;
    private Animation animation ;

    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // animation for logo image
        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        splashFragmentImLogo.setAnimation(animation);
        //navController to move the next fragment
        navController = Navigation.findNavController(view);
        // splash
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navController.navigate(R.id.action_splashFragment_to_startFragment);
            }
        }, timeSplash);
    }
}
