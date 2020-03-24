package com.hamoda.sofra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.hamoda.sofra.R;
import com.hamoda.sofra.data.model.allRestaurant.AllRestaurantData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {


    private Context context;
    private AppCompatActivity activity;
    private NavController navController;
    // this data not right
    private List<AllRestaurantData> data = new ArrayList<>();

    public FoodListAdapter(AppCompatActivity activity, NavController navController, List<AllRestaurantData> data) {
        this.activity = activity;
        this.context = activity;
        this.navController = navController;
        this.data = data;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodListViewHolder(LayoutInflater.from(context).inflate(R.layout.food_list_item
                , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListViewHolder holder, int position) {
        setData(holder,position);
        setAction(holder,position);
    }

    private void setAction(FoodListViewHolder holder, int position) {

    }

    private void setData(FoodListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FoodListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.food_list_item_iv_image)
        ImageView foodListItemIvImage;
        @BindView(R.id.food_list_item_tv_item_name)
        TextView foodListItemTvItemName;
        @BindView(R.id.food_list_item_tv_item_description)
        TextView foodListItemTvItemDescription;
        @BindView(R.id.food_list_item_tv_item_price)
        TextView foodListItemTvItemPrice;

        private View view;

        public FoodListViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

        }
    }
}
