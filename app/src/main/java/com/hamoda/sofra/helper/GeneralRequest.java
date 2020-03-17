package com.hamoda.sofra.helper;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.hamoda.sofra.R;
import com.hamoda.sofra.adapter.SpinnerAdapter;
import com.hamoda.sofra.data.model.city.City;
import com.hamoda.sofra.data.model.city.CityData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hamoda.sofra.helper.HelperMethod.progressDialog;
import static com.hamoda.sofra.helper.HelperMethod.showProgressDialog;

public class GeneralRequest {

    public static void getSpinnerData(Activity activity, Call<City> call, final Spinner spinner
            , final SpinnerAdapter adapter, final Integer selectedId, final String hint, View view, final boolean show) {
        if (show) {
            showProgressDialog(view, R.id.all_restaurant_fragment_progress_bar);
        }
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                if (show) {
                    HelperMethod.dismissProgressDialog();
                }
                try {
                    if (response.body().getStatus() == 1) {
                        if (view != null) {
                            view.setVisibility(view.VISIBLE);
                        }
                    }

                    adapter.setData(response.body().getData().getData(), hint);
                    spinner.setAdapter(adapter);
                    for (int i = 0; i < adapter.generalResponseDataList.size(); i++) {

                        if (adapter.generalResponseDataList.get(i).getId() == selectedId) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                if (show) {
                    HelperMethod.dismissProgressDialog();
                }
            }
        });
    }

    public static void getSpinnerData(Activity activity, Call<City> call, final Spinner spinner, final SpinnerAdapter adapter, final Integer selectedId, final String hint, View view, AdapterView.OnItemSelectedListener listener) {

        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {

                try {

                    adapter.setData(response.body().getData().getData(), hint);
                    spinner.setAdapter(adapter);
                    for (int i = 0; i < adapter.generalResponseDataList.size(); i++) {

                        if (adapter.generalResponseDataList.get(i).getId() == selectedId) {
                            spinner.setSelection(i);
                            break;
                        }
                    }

                    spinner.setOnItemSelectedListener(listener);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }

}
