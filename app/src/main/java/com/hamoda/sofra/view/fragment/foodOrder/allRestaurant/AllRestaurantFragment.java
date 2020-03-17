package com.hamoda.sofra.view.fragment.foodOrder.allRestaurant;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hamoda.sofra.R;
import com.hamoda.sofra.adapter.AllRestaurantAdapter;
import com.hamoda.sofra.adapter.SpinnerAdapter;
import com.hamoda.sofra.data.model.allRestaurant.AllRestaurant;
import com.hamoda.sofra.data.model.allRestaurant.AllRestaurantData;
import com.hamoda.sofra.helper.OnEndLess;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hamoda.sofra.data.api.RetrofitClient.getClient;
import static com.hamoda.sofra.helper.GeneralRequest.getSpinnerData;


public class AllRestaurantFragment extends Fragment {

    @BindView(R.id.all_restaurant_fragment_sp_city)
    Spinner allRestaurantFragmentSpCity;
    @BindView(R.id.all_restaurant_fragment_et_search_city)
    EditText allRestaurantFragmentEtSearchCity;
    @BindView(R.id.all_restaurant_fragment_btn_search)
    ImageButton allRestaurantFragmentBtnSearch;
    @BindView(R.id.all_restaurant_fragment_rv_restaurant_list)
    RecyclerView allRestaurantFragmentRvRestaurantList;
    @BindView(R.id.all_restaurant_fragment_srl_swipe_refresh_layout)
    SwipeRefreshLayout allRestaurantFragmentSrlSwipeRefreshLayout;

    private LinearLayoutManager linearLayoutmanger;
    private List<AllRestaurantData> restaurantData = new ArrayList<>();
    private AllRestaurantAdapter restaurantAdapter;

    private SpinnerAdapter citiesAdapter;
    private int citiesSelectedId = 0;

    private Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean filter = false;

    private NavController navController;

    public AllRestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_restaurant, container, false);
        ButterKnife.bind(this, view);

        restaurantData = new ArrayList<>();
        getSpinner(view);
        setPagination();

        return view;
    }

    // this method use to load more data when you reach the end of item or load new data
    private void setPagination() {
        // set layout
        linearLayoutmanger = new LinearLayoutManager(getActivity());
        allRestaurantFragmentRvRestaurantList.setLayoutManager(linearLayoutmanger);

        // ready class help to make pagination
        onEndLess = new OnEndLess(linearLayoutmanger, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;

                        if (filter) {
                            getFilterRestaurant(current_page);
                        } else {
                            getAllRestaurant(current_page);
                        }
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };

        // set recycler view on scroll to load the other data
        allRestaurantFragmentRvRestaurantList.addOnScrollListener(onEndLess);

        //set Adapter
        restaurantAdapter = new AllRestaurantAdapter((AppCompatActivity) getActivity(), restaurantData, navController);
        allRestaurantFragmentRvRestaurantList.setAdapter(restaurantAdapter);

        // use to reset the data and load again when you swipe to refresh - user in filter @_^
        allRestaurantFragmentSrlSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllRestaurant(1);
            }
        });

        if (restaurantData.size() == 0) {
            getAllRestaurant(1);
        }

    }

    private void getFilterRestaurant(int page) {
        filter = true;
        String citySearch = allRestaurantFragmentEtSearchCity.getText().toString();
        Call<AllRestaurant> call = getClient().getFilterRestaurant(citySearch, citiesSelectedId);
        startCall(call, page);
    }

    private void getAllRestaurant(int page) {
        Call<AllRestaurant> call = getClient().getAllRestaurant(page);
        startCall(call, page);
    }

    private void startCall(Call<AllRestaurant> call, int page) {

        call.enqueue(new Callback<AllRestaurant>() {
            @Override
            public void onResponse(Call<AllRestaurant> call, Response<AllRestaurant> response) {
                try {

                    // to stop refresh
                    allRestaurantFragmentSrlSwipeRefreshLayout.setRefreshing(false);

                    // this condition for reset data  -- because resetData method
                    if (response.body().getStatus() == 1) {
                        if (page == 1) {
                            onEndLess.current_page = 1;
                            onEndLess.previous_page = 1;
                            onEndLess.previousTotal = 0;

                            // set adapter again - because we reset data in swipe
                            restaurantData = new ArrayList<>();
                            restaurantAdapter = new AllRestaurantAdapter((AppCompatActivity) getActivity(), restaurantData, navController);
                            allRestaurantFragmentRvRestaurantList.setAdapter(restaurantAdapter);
                        }
                    }

                    // load the data from api
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        restaurantData.addAll(response.body().getData().getData());
                        restaurantAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<AllRestaurant> call, Throwable t) {
                // to stop refresh
                allRestaurantFragmentSrlSwipeRefreshLayout.setRefreshing(false);
//                Toast.makeText( getActivity(),t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("error", "onFailure: " + t.getMessage());
            }
        });
    }

    private void getSpinner(View view) {

        citiesAdapter = new SpinnerAdapter(getActivity());
        getSpinnerData(getActivity()
                , getClient().getCities()
                , allRestaurantFragmentSpCity
                , citiesAdapter
                , citiesSelectedId
                , "مدينة", view, true);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.all_restaurant_fragment_btn_search)
    public void onViewClicked() {
        citiesSelectedId = citiesAdapter.selectedId;
        if (citiesSelectedId == 0) {
            getAllRestaurant(1);
        } else {
            getFilterRestaurant(1);
        }
    }
}
