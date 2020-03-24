package com.hamoda.sofra.view.fragment.foodOrder.foodOrderHome.restaurantDetails.foodList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hamoda.sofra.R;
import com.hamoda.sofra.helper.HelperMethod;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.hamoda.sofra.helper.HelperMethod.dismissProgressDialog;
import static com.hamoda.sofra.helper.HelperMethod.showProgressDialog;


public class FoodListFragment extends Fragment implements IFoodList.view {

    @BindView(R.id.food_list_fragment_rv_restaurant_food_type)
    RecyclerView foodListFragmentRvRestaurantFoodType;
    @BindView(R.id.food_list_fragment_rv_restaurant_food_list)
    RecyclerView foodListFragmentRvRestaurantFoodList;
    @BindView(R.id.food_list_fragment_srl_swipe_refresh_layout)
    SwipeRefreshLayout foodListFragmentSrlSwipeRefreshLayout;

    private View view;
    FoodListPresenterImpl foodListPresenter;
    private int restaurantId;

    public FoodListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_food_list, container, false);

        Bundle bundle = new Bundle();
        restaurantId = bundle.getInt("retaurant_id");

        foodListFragmentRvRestaurantFoodType.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        foodListPresenter = new FoodListPresenterImpl(this);
        foodListPresenter.onCreate();
        foodListPresenter.getRestaurantFoodTypeList(restaurantId, 1);

        return view;
    }

    @Override
    public void init() {
        ButterKnife.bind(this, view);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onRestaurantFoodListSuccess() {

    }

    @Override
    public void onRestaurantFoodTypeListSuccess() {

    }

    @Override
    public void showProgress() {
        showProgressDialog(view, R.id.food_list_fragment_progress_bar);
    }

    @Override
    public void hideprogress() {
        dismissProgressDialog();
    }
}
