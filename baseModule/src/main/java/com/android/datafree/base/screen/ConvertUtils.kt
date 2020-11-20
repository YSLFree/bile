package com.android.datafree.base.screen

import android.content.Context

class ConvertUtils {
    companion object{
        /**
         * dp值转换成px值
         *
         * @param dpValue dp值
         * @return px值
         */
        fun dp2px(context: Context, dpValue: Float): Int {
            val scale: Float = context.getResources().getDisplayMetrics().density
            return (dpValue * scale + 0.5f).toInt()
        }

        /**
         * px值转换成dp值
         *
         * @param pxValue px值
         * @return dp值
         */
        fun px2dp(context: Context, pxValue: Float): Int {
            val scale: Float = context.getResources().getDisplayMetrics().density
            return (pxValue / scale + 0.5f).toInt()
        }

        /**
         * sp值转换成px值
         *
         * @param spValue sp值
         * @return px值
         */
        fun sp2px(context: Context, spValue: Float): Int {
            val fontScale: Float = context.getResources().getDisplayMetrics().scaledDensity
            return (spValue * fontScale + 0.5f).toInt()
        }

        /**
         * px值转换成sp值
         *
         * @param pxValue px值
         * @return sp值
         */
        fun px2sp(context: Context, pxValue: Float): Int {
            val fontScale: Float = context.getResources().getDisplayMetrics().scaledDensity
            return (pxValue / fontScale + 0.5f).toInt()
        }
    }
}