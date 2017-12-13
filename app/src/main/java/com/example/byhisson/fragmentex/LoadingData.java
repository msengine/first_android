package com.example.byhisson.fragmentex;

import android.view.WindowManager;
import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by byhisson on 2017. 12. 13..
 */

public class LoadingData {
    private static Dialog m_Loadingdialog = null;

    public static void showLoadingDialog(Context context) {
        if (m_Loadingdialog == null) {
            showCustomDialog(context);
        } else if (!m_Loadingdialog.isShowing()) {
            m_Loadingdialog = null;
            showCustomDialog(context);
        }
    }

    public static void hideLoadingDialog() {
        if (m_Loadingdialog != null) {
            if (m_Loadingdialog.isShowing()) {
                m_Loadingdialog.dismiss();
                m_Loadingdialog = null;
            }
        }
    }

    public static void showCustomDialog(Context context) {
        m_Loadingdialog = new Dialog(context, R.style.custom_loading);
        ProgressBar pb = new ProgressBar(context);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        m_Loadingdialog.setContentView(pb, params);
        m_Loadingdialog.setCancelable(false);
        m_Loadingdialog.show();

    }
}
