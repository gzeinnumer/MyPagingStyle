package com.gzeinnumer.mypagingstyle.repository.coin;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.lifecycle.MutableLiveData;

import com.gzeinnumer.mypagingstyle.base.BaseConstant;
import com.gzeinnumer.mypagingstyle.base.BaseResource;
import com.gzeinnumer.mypagingstyle.base.response.BaseListResponse;
import com.gzeinnumer.mypagingstyle.data.network.ApiService;
import com.gzeinnumer.mypagingstyle.data.network.RetroServer;
import com.gzeinnumer.mypagingstyle.helper.GblFunction;
import com.gzeinnumer.mypagingstyle.model.response.CoinHistoryResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CoinRepoImpl implements CoinRepo {

//    private final CoinRepoImpl coinRepo;
//    coinRepo = new CoinRepoImpl(application.getApplicationContext());

    private final ApiService apiService;
    private final CompositeDisposable compositeDisposable;
    private final ConnectivityManager cm;

    private boolean isConnect() {
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    public CoinRepoImpl(Context context) {
        apiService = RetroServer.getInstance(context);
        compositeDisposable = new CompositeDisposable();
        cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        coinHistory = new MutableLiveData<>();
    }

    private final MutableLiveData<BaseResource<List<CoinHistoryResponse>>> coinHistory;

    public void setCoinHistory(
                               int page,
                               String status,
                               String start_date,
                               String end_date) {
        coinHistory.postValue(BaseResource.loading());
        compositeDisposable.add(
                apiService.getCoinHistory(BaseConstant.PERPAGE, page, 195, status, start_date, end_date)
//                apiService.getCoinHistory(BaseConstant.PERPAGE, page, 180, status, start_date, end_date)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            int code = response.code();
                            BaseListResponse<CoinHistoryResponse> res = response.body();
                            if (res.getStatus().equals(BaseConstant.RES_SUCCESS)) {
                                List<CoinHistoryResponse> list = response.body().getData();
                                coinHistory.postValue(BaseResource.success(res.getTitle(), res.getMessage(), list, res.getTotal()));
                            } else if (res.getStatus().equals(BaseConstant.RES_TC)) {
                                coinHistory.postValue(BaseResource.error(res.getTitle(), "Gagal mengambil data, silahkan check koneksi internet anda."));
                            } else {
                                String msg = GblFunction.msgDebugOrRelease(response.toString() + "\n\n" + res.getMessage(), res.getMessage());
                                coinHistory.postValue(BaseResource.error(res.getTitle(), msg));
                            }
                        }, throwable -> {
                            String msg = GblFunction.msgDebugOrRelease(throwable.getMessage(), "Gagal mendapatkan data");
                            coinHistory.postValue(BaseResource.error("Error ", msg));
                        })
        );
    }

    public MutableLiveData<BaseResource<List<CoinHistoryResponse>>> getCoinHistory() {
        return coinHistory;
    }
}
