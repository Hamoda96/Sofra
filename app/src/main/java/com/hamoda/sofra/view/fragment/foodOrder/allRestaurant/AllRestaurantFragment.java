package com.hamoda.sofra.view.fragment.foodOrder.allRestaurant;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.hamoda.sofra.R;
import com.hamoda.sofra.adapter.AllRestaurantAdapter;
import com.hamoda.sofra.adapter.SpinnerAdapter;
import com.hamoda.sofra.data.model.allRestaurant.AllRestaurant;
import com.hamoda.sofra.data.model.allRestaurant.AllRestaurantData;
import com.hamoda.sofra.data.model.city.City;
import com.hamoda.sofra.helper.HelperMethod;
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
import static com.hamoda.sofra.helper.HelperMethod.disappearKeypad;


public class AllRestaurantFragment extends Fragment implements IAllRestaurant.View {

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
    private String citySearch;

    private Integer maxPage = 0;
    private OnEndLess onEndLess;
    private boolean filter = false;


    private View view;

    private NavController navController;

    AllRestaurantPresenterImpl allRestaurantPresenter;


    public AllRestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_all_restaurant, container, false);

        restaurantData = new ArrayList<>();
        allRestaurantPresenter = new AllRestaurantPresenterImpl(this);
        allRestaurantPresenter.onCreated();
        allRestaurantPresenter.getSpinner();
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
                            allRestaurantPresenter.getAllRestaurantFilter(current_page, citySearch, citiesSelectedId);
                        } else {
                            allRestaurantPresenter.getAllRestaurant(current_page);
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
//        restaurantAdapter = new AllRestaurantAdapter((AppCompatActivity) getActivity(), restaurantData, navController);
//        allRestaurantFragmentRvRestaurantList.setAdapter(restaurantAdapter);

        // use to reset the data and load again when you swipe to refresh - user in filter @_^
        allRestaurantFragmentSrlSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (filter) {
                    allRestaurantPresenter.getAllRestaurantFilter(1, citySearch, citiesSelectedId);

                } else {
                    allRestaurantPresenter.getAllRestaurant(1);
                }
            }
        });

        if (restaurantData.size() == 0) {
            if (filter) {
                allRestaurantPresenter.getAllRestaurantFilter(1, citySearch, citiesSelectedId);

            } else {
                allRestaurantPresenter.getAllRestaurant(1);
            }
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
    }

    @Override
    public void init() {
        ButterKnife.bind(this, view);

    }

    @Override
    public void setListener() {
        citySearch = allRestaurantFragmentEtSearchCity.getText().toString();
        allRestaurantFragmentBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disappearKeypad(getActivity(), view);
                citiesSelectedId = citiesAdapter.selectedId;
                if (citiesSelectedId == 0) {
                    allRestaurantPresenter.getAllRestaurant(1);

                } else {
                    filter = true;
                    allRestaurantPresenter.getAllRestaurantFilter(1, citySearch, citiesSelectedId);
                }
            }
        });
    }

    @Override
    public void onGetRestaurantSuccess(AllRestaurant allRestaurant, int page) {
        try {
            // to stop refresh
            allRestaurantFragmentSrlSwipeRefreshLayout.setRefreshing(false);
            // this condition for reset data  -- because resetData method
            if (allRestaurant.getStatus() == 1) {
                if (page == 1) {
                    Log.d("page", "onGetRestaurantSuccess: " + page);
                    onEndLess.current_page = 1;
                    onEndLess.previous_page = 1;
                    onEndLess.previousTotal = 0;

                    // set adapter again - because we reset data in swipe
                    restaurantAdapter = new AllRestaurantAdapter((AppCompatActivity) getActivity(), restaurantData, navController);
                    allRestaurantFragmentRvRestaurantList.setAdapter(restaurantAdapter);
                }
            }

            // load the data from api
            if (allRestaurant.getStatus() == 1) {
                maxPage = allRestaurant.getData().getLastPage();
                restaurantData.addAll(allRestaurant.getData().getData());
                restaurantAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onGetRestaurantFilterSuccess(AllRestaurant allRestaurant, int page) {
        try {
            Toast.makeText(getActivity(), "ggggggggg", Toast.LENGTH_SHORT).show();
            // to stop refresh
            allRestaurantFragmentSrlSwipeRefreshLayout.setRefreshing(false);
            // this condition for reset data  -- because resetData method
            if (allRestaurant.getStatus() == 1) {
                if (page == 1) {
                    Log.d("page", "onGetRestaurantSuccess: " + page);
                    onEndLess.current_page = 1;
                    onEndLess.previous_page = 1;
                    onEndLess.previousTotal = 0;

                    // set adapter again - because we reset data in swipe
                    restaurantAdapter = new AllRestaurantAdapter((AppCompatActivity) getActivity(), restaurantData, navController);
                    allRestaurantFragmentRvRestaurantList.setAdapter(restaurantAdapter);
                }
            }

            // load the data from api
            if (allRestaurant.getStatus() == 1) {
                maxPage = allRestaurant.getData().getLastPage();
                restaurantData.addAll(allRestaurant.getData().getData());
                restaurantAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onGetSpinnerSuccess(City city) {
        citiesAdapter = new SpinnerAdapter(getActivity());
        if (city.getStatus() == 1) {
            citiesAdapter.setData(city.getData().getData(), "Haaa");
            allRestaurantFragmentSpCity.setAdapter(citiesAdapter);
            for (int i = 0; i < citiesAdapter.generalResponseDataList.size(); i++) {

                if (citiesAdapter.generalResponseDataList.get(i).getId() == citiesSelectedId) {
                    allRestaurantFragmentSpCity.setSelection(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onError(String error) {
        Toast.makeText(getActivity(), "Error is : " + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        HelperMethod.showProgressDialog(view, R.id.all_restaurant_fragment_progress_bar);
    }

    @Override
    public void hideProgress() {
        HelperMethod.dismissProgressDialog();
    }
}
