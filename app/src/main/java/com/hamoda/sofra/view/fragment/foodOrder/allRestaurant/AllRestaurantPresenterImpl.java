package com.hamoda.sofra.view.fragment.foodOrder.allRestaurant;

import android.app.Activity;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import com.hamoda.sofra.adapter.SpinnerAdapter;
import com.hamoda.sofra.data.api.RetrofitClient;
import com.hamoda.sofra.data.model.allRestaurant.AllRestaurant;
import com.hamoda.sofra.data.model.city.City;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.hamoda.sofra.data.api.RetrofitClient.getClient;
import static com.hamoda.sofra.helper.GeneralRequest.getSpinnerData;

public class AllRestaurantPresenterImpl implements IAllRestaurant.Presenter {

    private IAllRestaurant.View restaurantView;

    public AllRestaurantPresenterImpl(IAllRestaurant.View restaurantView) {
        this.restaurantView = restaurantView;
    }

    @Override
    public void onCreated() {
        restaurantView.init();
        restaurantView.setListener();
    }

    @Override
    public void getAllRestaurant(int page) {
        restaurantView.showProgress();
        getClient().getAllRestaurant(page).enqueue(new Callback<AllRestaurant>() {
            @Override
            public void onResponse(Call<AllRestaurant> call, Response<AllRestaurant> response) {
                restaurantView.hideProgress();
                // load the data from api
                if (response.body().getStatus() == 1) {
                    restaurantView.onGetRestaurantSuccess(response.body(), page);
                }
            }

            @Override
            public void onFailure(Call<AllRestaurant> call, Throwable t) {
                restaurantView.hideProgress();
                restaurantView.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getAllRestaurantFilter(int page, String citySearch, int cityId) {
        restaurantView.showProgress();
        getClient().getFilterRestaurant(citySearch, cityId).enqueue(new Callback<AllRestaurant>() {
            @Override
            public void onResponse(Call<AllRestaurant> call, Response<AllRestaurant> response) {

                restaurantView.hideProgress();
                // load the data from api
                if (response.body().getStatus() == 1) {
                    restaurantView.onGetRestaurantFilterSuccess(response.body(), page);
                }
            }

            @Override
            public void onFailure(Call<AllRestaurant> call, Throwable t) {
                restaurantView.hideProgress();
                restaurantView.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getSpinner() {
        restaurantView.showProgress();
       getClient().getCities().enqueue(new Callback<City>() {
           @Override
           public void onResponse(Call<City> call, Response<City> response) {
               restaurantView.hideProgress();
               if (response.body().getStatus()==1) {
                   restaurantView.onGetSpinnerSuccess(response.body());
                   Toast.makeText(restaurantView.getActivity(), "ssssss", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<City> call, Throwable t) {
               Toast.makeText(restaurantView.getActivity(), "fffffffff", Toast.LENGTH_SHORT).show();

               restaurantView.hideProgress();
               restaurantView.onError(t.getMessage());
           }
       });
    }



}
