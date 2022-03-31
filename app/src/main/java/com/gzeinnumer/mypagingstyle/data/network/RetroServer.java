package com.gzeinnumer.mypagingstyle.data.network;

import static com.gzeinnumer.mypagingstyle.helper.GblFunction.isDebugActive;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    //staging
    public static String base_url = "https://gofo-kao-dev.intishaka.com/api/";
    public static String base_url_file = "https://gofo-kao-dev.intishaka.com/";

    private static Retrofit setInit(Context applicationContext) {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static ApiService getInstance(Context applicationContext) {
        return setInit(applicationContext).create(ApiService.class);
    }
}
