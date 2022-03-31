package com.gzeinnumer.mypagingstyle.base.dialog;

import android.view.Gravity;

import com.gzeinnumer.mypagingstyle.R;
import com.gzeinnumer.mypagingstyle.base.BaseDialogFragment;

public class BaseFilterDialogFragment extends BaseDialogFragment {
    public BaseFilterDialogFragment() {
        super();
    }

    public BaseFilterDialogFragment(int style) {
        super(R.style.CustomDialogStyleFilter);
    }

    @Override
    public void onStart() {
        super.onStart();
        setGravity(Gravity.BOTTOM);
        setCanvasWidth(0.95);
    }
}
