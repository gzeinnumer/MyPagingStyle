package com.gzeinnumer.mypagingstyle.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.gzeinnumer.bu.utils.MBUtilsDate;
import com.gzeinnumer.mypagingstyle.BuildConfig;
import com.gzeinnumer.mypagingstyle.base.BaseConstant;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GblFunction {

    public static final String TAG = "GblFunction";

    public static void debugLocationActivity(Context applicationContext, String simpleName) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(applicationContext, simpleName, Toast.LENGTH_SHORT).show();
        }
    }

    public static void myLogD(String TAG, String msg) {
        Log.d(TAG, "myLogD: " + msg);
    }

    public static String getCreatedAt() {
        return MBUtilsDate.getCurrentTime("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }

    public static boolean isDebugActive() {

        return BuildConfig.DEBUG;
//        return false;
    }

    public static String msgDebugOrRelease(String debug, String realese) {
        if (BuildConfig.DEBUG) {
            return debug;
        } else {
            return realese;
        }
    }

    public static String saparator(String money) {
        if (money == null) {
            return "0";
        }
        return saparatorV2(money);
    }

    public static String saparator(int money) {
        return saparatorV2(s(money));
    }

    public static String saparator(double money) {
        return saparatorV2(s(money));
    }

    public static String saparatorV2(String s) {
        if (s == null || s.equals("") || s.equals("0")|| s.equals("0.0")) {
            return "0";
        }
        s = idr(s);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){
            return  s;
        }else {
            return s.substring(0, s.indexOf(","));
        }
    }

    public static String idr(String cureencyValue) {
        if (cureencyValue == null || cureencyValue.equals("")) {
            return "0";
        } else {
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            return formatRupiah.format(Double.valueOf(cureencyValue)).replace("Rp", "");
        }
    }

    public static String s(long str) {
        return String.valueOf(str);
    }

    public static String s(int str) {
        return String.valueOf(str);
    }

    public static String s(double str) {
        return String.valueOf(str);
    }

    public static String getYesterday(int count) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, count);
        Date date = cal.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(date);
    }

    public static String getTomorrow(int count) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, count);
        Date date = cal.getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static boolean checkBetween(String dateToCheck, String startDate, String endDate) {
        boolean res = false;
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd"); //22-05-2013
        SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd"); //22-05-2013
        try {
            Date requestDate = fmt2.parse(dateToCheck);
            Date fromDate = fmt1.parse(startDate);
            Date toDate = fmt1.parse(endDate);
            res = requestDate.compareTo(fromDate) >= 0 && requestDate.compareTo(toDate) <= 0;
        } catch (ParseException pex) {
            pex.printStackTrace();
        }
        return res;
    }

}
