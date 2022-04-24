package com.gzeinnumer.mypagingstyle.base;

import com.gzeinnumer.da.dialog.datePickerDialog.multi.MultiDatePickerDialog;
import com.gzeinnumer.edf.MyLibDialog;
import com.gzeinnumer.mypagingstyle.R;
import com.gzeinnumer.mypagingstyle.base.dialog.BasePopUp;

public class BaseDialogFragment extends MyLibDialog {

    public BaseDialogFragment() {
        super(R.style.CustomDialogStyle);
    }

    public BaseDialogFragment(int style) {
        super(style);
    }

    protected void onShowCustomToast(String msg) {
        BasePopUp.onShowCustomToast(requireActivity(), requireContext(), msg);
    }

    protected MultiDatePickerDialog datePickerMultiChild() {
        return BasePopUp.datePickerMulti(getChildFragmentManager(), requireActivity());
    }
}
