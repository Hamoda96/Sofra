
package com.hamoda.sofra.data.model.allRestaurant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllRestaurant {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private AllRestaurantPagination data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public AllRestaurantPagination getData() {
        return data;
    }

    public void setData(AllRestaurantPagination data) {
        this.data = data;
    }

}
