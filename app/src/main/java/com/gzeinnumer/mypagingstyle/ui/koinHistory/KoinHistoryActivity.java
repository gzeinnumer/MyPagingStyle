package com.gzeinnumer.mypagingstyle.ui.koinHistory;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.akiniyalocts.pagingrecycler.PagingDelegate;
import com.google.android.material.tabs.TabLayout;
import com.gzeinnumer.mypagingstyle.adapterPaging.CoinPager;
import com.gzeinnumer.mypagingstyle.base.activity.BaseActivityBlackWhiteNavigation;
import com.gzeinnumer.mypagingstyle.databinding.ActivityKoinHistoryBinding;
import com.gzeinnumer.mypagingstyle.model.params.PagingParams;
import com.gzeinnumer.mypagingstyle.model.response.CoinHistoryResponse;
import com.gzeinnumer.mypagingstyle.ui.dialog.filter.koinHistory.KoinHistoryFilterDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KoinHistoryActivity extends BaseActivityBlackWhiteNavigation {

    private ActivityKoinHistoryBinding binding;
    private KoinHistoryVM vm;
    private PagingParams params = new PagingParams();
    private List<CoinHistoryResponse> list = new ArrayList<>();
    private CoinPager adapter;
    private String edStatusId = "153";
    private String edStatusName = "";
    private String start_date = "";
    private String end_date = "";

    @Override
    protected void onResume() {
        super.onResume();
        params = new PagingParams();
        initKoinPager();
        switch (edStatusId) {
            case "151":
                Objects.requireNonNull(binding.tabLayout.getTabAt(1)).select();
                break;
            case "152":
                Objects.requireNonNull(binding.tabLayout.getTabAt(2)).select();
                break;
            case "153":
            default:
                Objects.requireNonNull(binding.tabLayout.getTabAt(0)).select();
                break;
        }
        vm.setCoinHistory(1, edStatusId, start_date, end_date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityKoinHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = new ViewModelProvider(this).get(KoinHistoryVM.class);

        initView();
        initTextWatcher();
        initObserver();
        initOnClick();
    }

    private void initView() {
        initViewPager();
    }

    private void initTextWatcher() {

    }

    private void initObserver() {
        vm.getCoinHistory().observe(this, resource -> {
            switch (resource.status) {
                case STATUS_1_SUCCESS:
                    swipe(binding.swipeRefreshLayout, false);
                    list = new ArrayList<>();
                    list = resource.data;
                    emptyState(list != null ? list.size() : 0, binding.rv, binding.imgEmpty);
                    params.setTotalData(resource.total);
                    adapter.addNewItem(resource.data);
                    break;
                case STATUS_2_ERROR:
                    swipe(binding.swipeRefreshLayout, false);
                    onShowInfoDialogError(resource.title, resource.message);
                    emptyState(list != null ? list.size() : 0, binding.rv, binding.imgEmpty);
                    break;
                case STATUS_6_LOADING:
                    swipe(binding.swipeRefreshLayout, true);
                    emptyState(list != null ? list.size() : 0, binding.rv, binding.imgEmpty);
                    break;
            }
        });
    }

    private void initOnClick() {
        debugLocationActivity(binding.tvToolbar, getClass().getSimpleName());
        binding.btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
        binding.btnPopup.setOnClickListener(view -> {
            showFilterDialog();
        });

        binding.swipeRefreshLayout.setOnRefreshListener(() -> {
            onResume();
        });

        binding.imgClearFilter.setOnClickListener(v -> {
            start_date = "";
            end_date = "";
            binding.tvFilterText.setText("");
            binding.llFilterText.setVisibility(View.GONE);
            onResume();
        });
    }

    private void initViewPager() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                List<TabLayout.Tab> tabItems = new ArrayList<>();
                tabItems.add(binding.tabLayout.getTabAt(0));
                tabItems.add(binding.tabLayout.getTabAt(1));
                tabItems.add(binding.tabLayout.getTabAt(2));
                for (int i = 0; i < tabItems.size(); i++) {
                    tabItems.get(i).view.setEnabled(false);
                }

                switch (tab.getPosition()) {
                    case 0:
                        edStatusId = "153";
                        onResume();
                        break;
                    case 1:
                        edStatusId = "151";
                        onResume();
                        break;
                    case 2:
                        edStatusId = "152";
                        onResume();
                        break;
                }

                new Handler().postDelayed(() -> {
                    for (int i = 0; i < tabItems.size(); i++) {
                        tabItems.get(i).view.setEnabled(true);
                    }
                }, 1500);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void showFilterDialog() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment previous = getSupportFragmentManager().findFragmentByTag(KoinHistoryFilterDialog.TAG);
        if (previous != null) {
            transaction.remove(previous);
        }
        KoinHistoryFilterDialog dialog = KoinHistoryFilterDialog.newInstance(edStatusId, edStatusName, start_date, end_date, (edStatusId, edStatusName, firstDate, lastDate) -> {
            this.edStatusId = edStatusId;
            this.edStatusName = edStatusName;
            this.start_date = firstDate;
            this.end_date = lastDate;

            if (start_date.length()>0 || end_date.length()>0){
                binding.tvFilterText.setText(start_date+" sd "+end_date);
                binding.llFilterText.setVisibility(View.VISIBLE);
            } else {
                binding.tvFilterText.setText("");
                binding.llFilterText.setVisibility(View.GONE);
            }
            onResume();
        });
        dialog.show(transaction, KoinHistoryFilterDialog.TAG);
    }

    private void initKoinPager() {
        list = new ArrayList<>();
        adapter = new CoinPager();
        adapter.setCallBack((type, position, data) -> {

        });
        adapter.setBaseDebugCallback((type, position, data) -> {
            debugDialog(data.toString());
        });
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rv.hasFixedSize();

        new PagingDelegate.Builder(adapter)
                .attachTo(binding.rv)
                .listenWith(new PagingDelegate.OnPageListener() {
                    @Override
                    public void onPage(int i) {
                        if (params.getCurrentPage() < params.getTotalPage()) {
                            params.addCurrentPage();
                            vm.setCoinHistory(params.getCurrentPage(), edStatusId, start_date, end_date);
                        } else {
                            onDonePaging();
                        }
                    }

                    @Override
                    public void onDonePaging() {

                    }
                })
                .build();
    }

    private void updateOnNotif() {
        onResume();
    }

}