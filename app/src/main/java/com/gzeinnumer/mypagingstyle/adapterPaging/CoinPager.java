package com.gzeinnumer.mypagingstyle.adapterPaging;

import static com.gzeinnumer.mypagingstyle.helper.GblFunction.isDebugActive;
import static com.gzeinnumer.mypagingstyle.helper.GblFunction.saparator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.akiniyalocts.pagingrecycler.PagingAdapter;
import com.gzeinnumer.mypagingstyle.R;
import com.gzeinnumer.mypagingstyle.base.BaseCallBack;
import com.gzeinnumer.mypagingstyle.base.BaseConstant;
import com.gzeinnumer.mypagingstyle.base.BaseDebugCallback;
import com.gzeinnumer.mypagingstyle.databinding.ItemKoinHistoryBinding;
import com.gzeinnumer.mypagingstyle.model.response.CoinHistoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CoinPager extends PagingAdapter {
    private final List<CoinHistoryResponse> list = new ArrayList<>();
    private Context context;
    private BaseDebugCallback<CoinHistoryResponse> baseDebugCallback;

    public void setBaseDebugCallback(BaseDebugCallback<CoinHistoryResponse> baseDebugCallback) {
        this.baseDebugCallback = baseDebugCallback;
    }

    public CoinPager() {}

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        return new MyHolder(ItemKoinHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), BaseConstant.RV_ANIM));

        MyHolder myHolder = (MyHolder) holder;
        myHolder.bind(list.get(position), position, callBack);
        prepareSpace(myHolder.itemBinding.cv, position);
        if (baseDebugCallback!=null){
            if (isDebugActive()){
                myHolder.itemBinding.getRoot().setOnLongClickListener(v -> {
                    baseDebugCallback.onClick(1, position, list.get(position));
                    return false;
                });
            }
        }
    }

    @Override
    public int getPagingLayout() {
        return R.layout.item_koin_history;
    }

    @Override
    public int getPagingItemCount() {
        return list.size();
    }

    public void addNewItem(List<CoinHistoryResponse> data) {
        int lastSize = list.size();
        for (CoinHistoryResponse d: data){
            if (!list.contains(d))
                list.add(d);
        }
        notifyItemInserted(lastSize);
    }

    public int intToDp(int sizeInDPH) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, sizeInDPH, context.getResources().getDisplayMetrics());
    }

    private void prepareSpace(CardView parent, int position) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) parent.getLayoutParams();

        int topBottomRv = 10;
        int leftRightItem = 10;
        int spaceBetween = 10 / 2;
        if (position == 0) {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(topBottomRv), intToDp(leftRightItem), intToDp(spaceBetween));
        } else if (position == list.size() - 1) {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(spaceBetween), intToDp(leftRightItem), intToDp(topBottomRv));
        } else {
            layoutParams.setMargins(intToDp(leftRightItem), intToDp(spaceBetween), intToDp(leftRightItem), intToDp(spaceBetween));
        }
        parent.setLayoutParams(layoutParams);
    }

    private BaseCallBack<CoinHistoryResponse> callBack;

    public void setCallBack(BaseCallBack<CoinHistoryResponse> callBack) {
        this.callBack = callBack;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        public ItemKoinHistoryBinding itemBinding;

        public MyHolder(@NonNull ItemKoinHistoryBinding itemView) {
            super(itemView.getRoot());
            this.itemBinding = itemView;
        }

        @SuppressLint("SetTextI18n")
        public void bind(CoinHistoryResponse data, int position, BaseCallBack<CoinHistoryResponse> callBack) {
                itemBinding.tvNoPesanan.setText("No. Pesanan: "+data.getNoOrderCredit());
        }
    }
}
