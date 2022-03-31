package com.gzeinnumer.mypagingstyle.ui.koinHistory;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gzeinnumer.mypagingstyle.base.BaseResource;
import com.gzeinnumer.mypagingstyle.model.response.CoinHistoryResponse;
import com.gzeinnumer.mypagingstyle.repository.coin.CoinRepoImpl;

import java.util.List;

public class KoinHistoryVM extends AndroidViewModel {

    private final CoinRepoImpl coinRepo;

    public KoinHistoryVM(Application application) {
        super(application);
        coinRepo = new CoinRepoImpl(application.getApplicationContext());
        coinHistory = coinRepo.getCoinHistory();
    }

    private final LiveData<BaseResource<List<CoinHistoryResponse>>> coinHistory;

    public void setCoinHistory(
            int page,
            String status,
            String start_date,
            String end_date) {
        coinRepo.setCoinHistory(page, status, start_date, end_date);
    }

    public LiveData<BaseResource<List<CoinHistoryResponse>>> getCoinHistory() {
        return coinHistory;
    }
}