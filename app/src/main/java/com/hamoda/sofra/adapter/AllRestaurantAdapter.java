package com.hamoda.sofra.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamoda.sofra.R;
import com.hamoda.sofra.data.model.allRestaurant.AllRestaurantData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.hamoda.sofra.R.drawable.circle_shape_offline;
import static com.hamoda.sofra.R.drawable.circle_shape_online;

public class AllRestaurantAdapter extends RecyclerView.Adapter<AllRestaurantAdapter.RestaurantViewHolder> {


    private Context context;
    private AppCompatActivity activity;
    private List<AllRestaurantData> restaurantData = new ArrayList<>();
    private NavController navController;

    public AllRestaurantAdapter(AppCompatActivity activity, List<AllRestaurantData> restaurantData, NavController navController) {
        this.activity = (AppCompatActivity) activity;
        this.restaurantData = restaurantData;
        this.navController = navController;
        this.context = activity;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_item
                , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setAction(RestaurantViewHolder holder, int position) {
//        holder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @SuppressLint("SetTextI18n")
    private void setData(RestaurantViewHolder holder, int position) {
        holder.restaurantItemTvRestaurantName.setText(restaurantData.get(position).getName());
        String url = restaurantData.get(position).getPhotoUrl();
        Glide.with(context).load(url).into(holder.restaurantCivRestaurantImage);
        holder.restaurantItemTvStatusOpenClose.setText(restaurantData.get(position).getAvailability());
        if (restaurantData.get(position).getAvailability().equals("open")) {
            holder.restaurantItemTvStatusOpenClose.setText("مفتوح");
        }else {
            holder.restaurantItemTvStatusOpenClose.setText("مغلق");
        }
        holder.restaurantItemRbRatingbar.setRating((float) restaurantData.get(position).getRate());
        holder.restaurantItemTvRestaurantPriceDown.setText( "الحد الأدني للطلب :" + restaurantData.get(position).getMinimumCharger());
        holder.restaurantItemTvRestaurantDeliveryCharge.setText("رسوم التوصيل : "+restaurantData.get(position).getDeliveryCost());
        if (restaurantData.get(position).getActivated().equals("1")) {
            holder.restaurantItemTvStatusOpenCloseColor.setBackground(activity.getResources().getDrawable(circle_shape_online));
        }else {
            holder.restaurantItemTvStatusOpenCloseColor.setBackground(activity.getResources().getDrawable(circle_shape_offline));
        }
    }

    @Override
    public int getItemCount() {
        return restaurantData.size();
    }

    public void setList(List<AllRestaurantData> restaurantData){
        this.restaurantData =restaurantData;
        notifyDataSetChanged();
    }
    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.restaurant_item_tv_restaurant_name)
        TextView restaurantItemTvRestaurantName;
        @BindView(R.id.restaurant_item_tv_status_open_close_color)
        TextView restaurantItemTvStatusOpenCloseColor;
        @BindView(R.id.restaurant_item_tv_status_open_close)
        TextView restaurantItemTvStatusOpenClose;
        @BindView(R.id.restaurant_item_rb_ratingbar)
        RatingBar restaurantItemRbRatingbar;
        @BindView(R.id.restaurant_item_tv_restaurant_price_down)
        TextView restaurantItemTvRestaurantPriceDown;
        @BindView(R.id.restaurant_item_tv_restaurant_delivery_charge)
        TextView restaurantItemTvRestaurantDeliveryCharge;
        @BindView(R.id.restaurant_civ_restaurant_image)
        CircleImageView restaurantCivRestaurantImage;

        private View view;
        private int position;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
