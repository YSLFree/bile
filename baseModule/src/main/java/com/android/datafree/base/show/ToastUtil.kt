package com.android.datafree.base.show

import android.content.Context
import android.widget.Toast

class ToastUtil {
    companion object {
        private var toast: Toast? = null

        fun showShort(context: Context, msg: String) {
            if (toast != null) {
                toast?.cancel()
            }
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast?.show()
        }

        fun showLong(context: Context, msg: String) {
            if (toast != null) {
                toast?.cancel()
            }
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            toast?.show()
        }
    }
}