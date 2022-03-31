package com.gzeinnumer.mypagingstyle.base.response;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseListResponse<T> {

    @SerializedName("total")
    private int total;

    @SerializedName("data")
    private List<T> data;

    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    @SerializedName("info")
    private Info info;

    public int getTotal() {
        return total;
    }

    public List<T> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public Info getInfo(){
        return info;
    }

    @Override
    public String toString() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this, BaseListResponse.class);
    }
}
