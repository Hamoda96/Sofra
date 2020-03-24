package com.hamoda.sofra.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.hamoda.sofra.R;
import com.hamoda.sofra.view.fragment.foodOrder.foodOrderHome.restaurantDetails.comments.CommentsFragment;
import com.hamoda.sofra.view.fragment.foodOrder.foodOrderHome.restaurantDetails.foodList.FoodListFragment;
import com.hamoda.sofra.view.fragment.foodOrder.foodOrderHome.restaurantDetails.restaurantInfo.RestaurantInformationFragment;

public class RestaurantDetailsTapsAdapter extends FragmentStatePagerAdapter {

    public RestaurantDetailsTapsAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FoodListFragment();
        } else if (position == 1){
            return new CommentsFragment();
        }else {
            return new RestaurantInformationFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "قائمة الطعام";
        } else if (position == 1){
            return "التعليقات والتقييم";
        }else {
            return "معلومات المتجر";
        }

    }
}
