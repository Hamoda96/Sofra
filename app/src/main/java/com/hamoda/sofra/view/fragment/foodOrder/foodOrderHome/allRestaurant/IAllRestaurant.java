package com.hamoda.sofra.view.fragment.foodOrder.foodOrderHome.allRestaurant;

import android.app.Activity;

import com.hamoda.sofra.data.model.allRestaurant.AllRestaurant;
import com.hamoda.sofra.data.model.city.City;

public interface IAllRestaurant {

    interface View {
        void init();

        void setListener();

        void onGetRestaurantSuccess(AllRestaurant allRestaurant,int page);

        void onGetRestaurantFilterSuccess(AllRestaurant allRestaurant,int page);

        void onGetSpinnerSuccess(City city);

        void onError(String error);

        void showProgress();

        void hideProgress();

        Activity getActivity();
    }

    interface Presenter {
        void onCreated();

        void getAllRestaurant(int page);

        void getAllRestaurantFilter(int page , String citySearch , int cityId);

        void getSpinner();
    }
}
