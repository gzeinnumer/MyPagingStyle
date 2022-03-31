package com.gzeinnumer.mypagingstyle.ui.dialog.filter.koinHistory;

import static com.gzeinnumer.mypagingstyle.helper.GblFunction.getTomorrow;
import static com.gzeinnumer.mypagingstyle.helper.GblFunction.getYesterday;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.gzeinnumer.ad.AdapterAutoCompleteText;
import com.gzeinnumer.mypagingstyle.R;
import com.gzeinnumer.mypagingstyle.base.dialog.BaseFilterDialogFragment;
import com.gzeinnumer.mypagingstyle.databinding.DialogKoinHistoryFilterBinding;
import com.gzeinnumer.mypagingstyle.helper.GblFunction;

import java.util.ArrayList;
import java.util.List;

public class KoinHistoryFilterDialog extends BaseFilterDialogFragment {

    private final CallBack callBack;
    private DialogKoinHistoryFilterBinding binding;
    private KoinHistoryFilterVM vm;
    private List<String> list = new ArrayList<>();
    private String edStatusId = "";
    private String edStatusName = "";
    private final String firstDate = "";
    private final String lastDate = "";

    public KoinHistoryFilterDialog(String edStatusId, String edStatusName, String firstDate, String lastDate, CallBack callBack) {
//        this.edStatusId = edStatusId;
//        this.edStatusName = edStatusName;
//        this.firstDate = firstDate;
//        this.lastDate = lastDate;
        this.callBack = callBack;
    }

    public static KoinHistoryFilterDialog newInstance(String edStatusId, String edStatusName, String firstDate, String lastDate, CallBack callBac) {
        return new KoinHistoryFilterDialog(edStatusId, edStatusName, firstDate, lastDate, callBac);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DialogKoinHistoryFilterBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(KoinHistoryFilterVM.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        initTextWatcher();
        initObserver();
        initOnClick();
    }

    private void initView() {
        binding.edKeteranganId.setText(edStatusId);
        binding.edKeterangan.setText(edStatusName);
        binding.edMulaiDari.setText(firstDate);
        binding.edSampai.setText(lastDate);
        initStatus();
    }

    private void initTextWatcher() {

    }

    private void initObserver() {
    }

    private void initOnClick() {
        binding.edMulaiDari.setOnClickListener(view -> {
            showDialogDateRange();
        });
        binding.edSampai.setOnClickListener(view -> {
            showDialogDateRange();
        });
        binding.btnPilih.setOnClickListener(view -> {
            String fromDate = getYesterday(-90);
            String toDate = getTomorrow(90);
            String toCheck = binding.edMulaiDari.getText().toString();
            if (toCheck.length() > 0) {
                boolean isInRange = GblFunction.checkBetween(toCheck, fromDate, toDate);

                if (isInRange) {
                    callBack.dateRange(edStatusId, binding.edKeterangan.getText().toString(), binding.edMulaiDari.getText().toString(), binding.edSampai.getText().toString());
                    getDialog().dismiss();
                } else {
                    onShowCustomToast("Tidak boleh melebihi 90 hari");
                }
            } else {
                callBack.dateRange(edStatusId, binding.edKeterangan.getText().toString(), binding.edMulaiDari.getText().toString(), binding.edSampai.getText().toString());
                getDialog().dismiss();
            }
        });
        binding.btnPilihUlang.setOnClickListener(view -> {
            pilihUlangAction();
        });
    }

    private void initStatus() {
        AdapterAutoCompleteText<String> adapter = new AdapterAutoCompleteText<>(requireContext(), list);
        adapter.setItemStyle(R.style.AutoCompleteTextViewStyle);
        binding.edKeterangan.setAdapter(adapter);
        binding.edKeterangan.setFreezesText(false);

        binding.edKeterangan.setOnItemClickListener((parent, view, position, id) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                binding.edKeterangan.setText(list.get(position), false);
            } else {
                binding.edKeterangan.setText(list.get(position));
            }
            edStatusId = String.valueOf(list.get(position));
        });
    }

    private void showDialogDateRange() {
        datePickerMultiChild().onOkPressedCallBack((firstDate, secondDate) -> {
            binding.edMulaiDari.setText(firstDate);
            binding.edSampai.setText(secondDate);
        }).build().show();
    }

    private void pilihUlangAction() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            binding.edKeterangan.setText("", false);
        } else {
            binding.edKeterangan.setText("");
        }
        edStatusId = "153";
        edStatusName = "";
        binding.edKeteranganId.setText("153");
        binding.edMulaiDari.setText("");
        binding.edSampai.setText("");
    }

    public interface CallBack {
        void dateRange(String edStatusId, String edStatusName, String firstDate, String lastDate);
    }
}