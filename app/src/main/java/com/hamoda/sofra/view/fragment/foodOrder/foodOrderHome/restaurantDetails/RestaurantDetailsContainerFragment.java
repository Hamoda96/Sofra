package com.hamoda.sofra.view.fragment.foodOrder.foodOrderHome.restaurantDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.hamoda.sofra.R;
import com.hamoda.sofra.adapter.RestaurantDetailsTapsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RestaurantDetailsContainerFragment extends Fragment {

    @BindView(R.id.restaurant_details_container_fragment_tl_tabLayout)
    TabLayout restaurantDetailsContainerFragmentTlTabLayout;
    @BindView(R.id.restaurant_details_container_fragment_vp_viewPager)
    ViewPager restaurantDetailsContainerFragmentVpViewPager;

    public RestaurantDetailsContainerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_details_container, container, false);
        ButterKnife.bind(this, view);

        RestaurantDetailsTapsAdapter tapsAdapter = new RestaurantDetailsTapsAdapter(getChildFragmentManager());
        restaurantDetailsContainerFragmentVpViewPager.setAdapter(tapsAdapter);
        restaurantDetailsContainerFragmentTlTabLayout.setupWithViewPager(restaurantDetailsContainerFragmentVpViewPager);

        return view;
    }

}
