package com.gzeinnumer.mypagingstyle.data.network;

import com.gzeinnumer.mypagingstyle.base.response.BaseListResponse;
import com.gzeinnumer.mypagingstyle.model.response.CoinHistoryResponse;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v1/coin/history")
    Flowable<Response<BaseListResponse<CoinHistoryResponse>>> getCoinHistory(
            @Query("limit") int limit,
            @Query("page") int page,
            @Query("m_customer_gofo_id") int m_customer_gofo_id,
            @Query("status") String status,
            @Query("start_date") String start_date,
            @Query("end_date") String end_date
    );
}
