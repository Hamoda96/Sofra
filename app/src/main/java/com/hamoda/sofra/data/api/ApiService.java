package com.hamoda.sofra.data.api;



import com.hamoda.sofra.data.model.allRestaurant.AllRestaurant;
import com.hamoda.sofra.data.model.city.City;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @GET("cities")
    Call<City> getCities ();

    @GET("restaurants")
    Call<AllRestaurant> getAllRestaurant(@Query("page") int page);

    @GET("restaurants")
    Call<AllRestaurant> getFilterRestaurant(@Query("keyword") String keyword
            ,@Query("region_id") int regionId);
}