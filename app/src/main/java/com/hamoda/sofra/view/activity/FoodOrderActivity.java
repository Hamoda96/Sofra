package com.hamoda.sofra.view.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hamoda.sofra.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodOrderActivity extends AppCompatActivity {

    @BindView(R.id.food_order_activity_container_bn_bottomNavigation)
    BottomNavigationView foodOrderActivityContainerBnBottomNavigation;

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        navController = Navigation.findNavController(this, R.id.food_order_activity_fragment_container);
        NavigationUI.setupWithNavController(foodOrderActivityContainerBnBottomNavigation, navController);
    }
}
