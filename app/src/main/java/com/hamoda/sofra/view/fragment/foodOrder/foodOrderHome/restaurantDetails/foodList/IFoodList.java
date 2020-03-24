package com.hamoda.sofra.view.fragment.foodOrder.foodOrderHome.restaurantDetails.foodList;

import android.app.Activity;

public interface IFoodList {

    interface view {
        void init();

        void setListener();

        void onRestaurantFoodListSuccess();

        void onRestaurantFoodTypeListSuccess();

        void showProgress();

        void hideprogress();

        Activity getActivity();
    }

    interface presenter {

        void onCreate();

        void getRestaurantFoodList(int restaurant_id,int category_id);

        void getRestaurantFoodTypeList(int restaurant_id , int category_id);
    }
}
