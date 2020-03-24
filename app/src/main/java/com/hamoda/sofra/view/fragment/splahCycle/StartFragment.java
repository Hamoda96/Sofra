package com.hamoda.sofra.view.fragment.splahCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.hamoda.sofra.R;
import com.hamoda.sofra.view.activity.FoodOrderActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StartFragment extends Fragment {

    @BindView(R.id.start_fragment_im_food_logo_1)
    ImageView startFragmentImFoodLogo1;
    @BindView(R.id.start_fragment_im_food_logo_2)
    ImageView startFragmentImFoodLogo2;
    @BindView(R.id.start_fragment_btn_food_order)
    Button startFragmentBtnFoodOrder;
    @BindView(R.id.start_fragment_btn_buy_food)
    Button startFragmentBtnBuyFood;

    private NavController navController ;

    public StartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        setAnimation();
    }

    // Animation for logo food
    private void setAnimation() {
        startFragmentImFoodLogo1.setTranslationY(-500);
        startFragmentImFoodLogo1.setTranslationX(-500);
        startFragmentImFoodLogo1.animate().setDuration(1000).translationY(0).translationX(0);

        startFragmentImFoodLogo2.setTranslationY(500);
        startFragmentImFoodLogo2.setTranslationX(500);
        startFragmentImFoodLogo2.animate().setDuration(1000).translationY(0).translationX(0);
    }

    @OnClick({R.id.start_fragment_btn_food_order, R.id.start_fragment_btn_buy_food})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start_fragment_btn_food_order:
                Intent intent = new Intent(getActivity(), FoodOrderActivity.class);
                    startActivity(intent);
                break;
            case R.id.start_fragment_btn_buy_food:
                break;
        }
    }
}
