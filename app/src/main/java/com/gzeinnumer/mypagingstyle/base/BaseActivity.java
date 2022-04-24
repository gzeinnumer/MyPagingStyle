package com.gzeinnumer.mypagingstyle.base;

import static com.gzeinnumer.mypagingstyle.helper.GblFunction.isDebugActive;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.gzeinnumer.da.dialog.debug.DebugDialog;
import com.gzeinnumer.da.dialog.loadingDialog.LoadingDialog;
import com.gzeinnumer.mypagingstyle.R;
import com.gzeinnumer.mypagingstyle.base.activity.BasePermissionActivity;
import com.gzeinnumer.mypagingstyle.base.dialog.BasePopUp;
import com.gzeinnumer.mypagingstyle.helper.GblFunction;

public class BaseActivity extends BasePermissionActivity {

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            customType(this, BaseConstant.INTENT_ANIM_TYPE);
        }
    }

    protected void debugLocationActivity(View v, String TAG) {
        v.setOnClickListener(view -> {
            GblFunction.debugLocationActivity(getApplicationContext(), TAG);
        });
    }


    protected void onShowInfoDialogError(String title, String message) {
        BasePopUp.onShowInfoDialog(getSupportFragmentManager(), this).autoDismisOnSecond(-1).setTitle(title).setContent(message).show();
    }

    protected void debugDialog(String smg) {
        if (isDebugActive())
            new DebugDialog(getSupportFragmentManager())
                    .setAnimationStyle(R.style.CustomDialogStyle)
                    .setContent(smg)
                    .onOkPressedCallBack(() -> {
                        //ok action
                    })
                    .show();
    }

    protected void emptyState(int size, RecyclerView rv, LinearLayout imgEmpty) {
        rv.setVisibility(View.GONE);
        imgEmpty.setVisibility(View.GONE);

        rv.setVisibility(size == 0 ? View.GONE : View.VISIBLE);
        imgEmpty.setVisibility(size == 0 ? View.VISIBLE : View.GONE);
    }

    protected void swipe(SwipeRefreshLayout swipeRefreshLayout, boolean swipe) {
//        swipeRefreshLayout.setRefreshing(!swipeRefreshLayout.isRefreshing());
        swipeRefreshLayout.setRefreshing(swipe);
    }
}
