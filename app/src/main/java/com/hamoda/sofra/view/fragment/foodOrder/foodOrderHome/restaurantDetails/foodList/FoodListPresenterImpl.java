package com.hamoda.sofra.view.fragment.foodOrder.foodOrderHome.restaurantDetails.foodList;

public class FoodListPresenterImpl implements IFoodList.presenter {

    private IFoodList.view view;

    public FoodListPresenterImpl(IFoodList.view view) {
        this.view = view;
    }

    @Override
    public void onCreate() {
        view.init();
        view.setListener();
    }

    @Override
    public void getRestaurantFoodList(int restaurant_id, int category_id) {

    }

    @Override
    public void getRestaurantFoodTypeList(int restaurant_id, int category_id) {

    }
}
