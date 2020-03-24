package com.hamoda.sofra.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import de.hdodenhof.circleimageview.CircleImageView;

public class FoodListTypeAdapter extends RecyclerView.Adapter<FoodListTypeAdapter.FoodListTypeViewHolder> {


    private Context context;
    private AppCompatActivity activity;
    private NavController navController;
    private List<AllRestaurantData> data = new ArrayList<>();

    public FoodListTypeAdapter(AppCompatActivity activity, NavController navController, List<AllRestaurantData> data) {
        this.activity = activity;
        this.context = activity;
        this.navController = navController;
        this.data = data;
    }

    @NonNull
    @Override
    public FoodListTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodListTypeViewHolder(LayoutInflater.from(context).inflate(R.layout.food_list_type_item
                , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListTypeViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setAction(FoodListTypeViewHolder holder, int position) {

    }

    private void setData(FoodListTypeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FoodListTypeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.food_list_type_civ_image)
        CircleImageView foodListTypeCivImage;
        @BindView(R.id.food_list_type_tv_name)
        TextView foodListTypeTvName;

        private View view;

        public FoodListTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
