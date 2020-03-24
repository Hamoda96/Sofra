package com.hamoda.sofra.data.api;


import com.hamoda.sofra.data.model.allRestaurant.AllRestaurant;
import com.hamoda.sofra.data.model.city.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("cities")
    Call<City> getCities();

    @GET("restaurants")
    Call<AllRestaurant> getAllRestaurant(@Query("page") int page);


    @GET("restaurants")
    Call<AllRestaurant> getFilterRestaurant(@Query("page") int page,
                                            @Query("keyword") String keyword
            , @Query("city_id") int regionId);

    @GET("restaurants")
    Call<AllRestaurant> getFilterRestaurant(@Query("page") int page,
                                            @Query("keyword") String keyword);

    @GET("restaurant")
    Call<AllRestaurant> getRestaurantDetails(@Query("restaurant_id") int restaurantId);

    @GET("categories")
    Call<AllRestaurant> getRestaurantCategroy(@Query("restaurant_id") int restaurantId, @Query("category_id") int categoryId);

    // AllRestaurant not the right thing
    @GET("items")
    Call<AllRestaurant> getRestaurantFoodList(@Query("restaurant_id") int restaurantId, @Query("category_id") int categoryId);

}