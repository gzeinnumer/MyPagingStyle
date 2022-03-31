package com.gzeinnumer.mypagingstyle.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gzeinnumer.da.dialog.confirmDialog.ConfirmDialog;
import com.gzeinnumer.da.dialog.datePickerDialog.multi.MultiDatePickerDialog;
import com.gzeinnumer.da.dialog.datePickerDialog.single.SingleDatePickerDialog;
import com.gzeinnumer.da.dialog.debug.DebugDialog;
import com.gzeinnumer.da.dialog.infoDialog.InfoDialog;
import com.gzeinnumer.da.dialog.loadingDialog.LoadingDialog;
import com.gzeinnumer.mypagingstyle.R;
import com.gzeinnumer.mypagingstyle.base.dialog.BasePopUp;
import com.gzeinnumer.mypagingstyle.helper.GblFunction;

public class BaseFragment extends Fragment {
    private LoadingDialog loadingDialog;

    protected void onShowInfoDialogError(String title, String message) {
        BasePopUp.onShowInfoDialog(getChildFragmentManager(), requireActivity()).autoDismisOnSecond(-1).setTitle(title).setContent(message).show();
    }

    protected void emptyState(int size, View list, View emptyState) {
        list.setVisibility(View.GONE);
        emptyState.setVisibility(View.GONE);

        list.setVisibility(size==0 ? View.GONE : View.VISIBLE);
        emptyState.setVisibility(size==0 ? View.VISIBLE : View.GONE);
    }

    protected void swipe(SwipeRefreshLayout swipeRefreshLayout, boolean swipe){
//        swipeRefreshLayout.setRefreshing(!swipeRefreshLayout.isRefreshing());
        swipeRefreshLayout.setRefreshing(swipe);
    }

}
