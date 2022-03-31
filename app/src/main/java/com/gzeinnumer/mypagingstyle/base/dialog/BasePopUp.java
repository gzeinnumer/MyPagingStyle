package com.gzeinnumer.mypagingstyle.base.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.gzeinnumer.da.constant.ButtonStyle;
import com.gzeinnumer.da.constant.DialogType;
import com.gzeinnumer.da.dialog.confirmDialog.ConfirmDialog;
import com.gzeinnumer.da.dialog.datePickerDialog.multi.MultiDatePickerDialog;
import com.gzeinnumer.da.dialog.datePickerDialog.single.SingleDatePickerDialog;
import com.gzeinnumer.da.dialog.infoDialog.InfoDialog;
import com.gzeinnumer.da.dialog.loadingDialog.LoadingDialog;
import com.gzeinnumer.mypagingstyle.R;

public class BasePopUp {

    public static InfoDialog onShowInfoDialog(FragmentManager supportFragmentManager, FragmentActivity fragmentActivity) {
        return new InfoDialog(supportFragmentManager)
                .setAnimationStyle(R.style.CustomDialogStyle)
                .setButtonAllCaps(false)
                .autoDismisOnSecond(5)
                .setButtonGravity(Gravity.END)
                .setContentAlignment(View.TEXT_ALIGNMENT_TEXT_START)
                .setTitleAlignment(View.TEXT_ALIGNMENT_CENTER)
                .setTitleColor(fragmentActivity.getResources().getColor(R.color.colorPrimary))
                .setButtonStyle(ButtonStyle.ButtonText)
                .setBtnOkTitleColor(fragmentActivity.getResources().getColor(R.color.colorPrimary))
                .setDialogType(DialogType.DialogSuccess);
    }

    public static MultiDatePickerDialog datePickerMulti(FragmentManager supportFragmentManager, FragmentActivity fragmentActivity) {
        return new MultiDatePickerDialog(supportFragmentManager)
                .setTimeZone("GMT")
                .setTitle("Pilih tanggal")
                .setTimeFormat("yyyy-MM-dd");
    }

    public static void onShowCustomToast(FragmentActivity fragmentActivity, Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}