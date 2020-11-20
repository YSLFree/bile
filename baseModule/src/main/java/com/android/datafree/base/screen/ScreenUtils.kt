package com.android.datafree.base.screen

import android.R
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager


class ScreenUtils {
    companion object {
        /**
         * 获取屏幕宽度
         *
         * @param context Context
         * @return 屏幕宽度（px）
         */
        fun getScreenWidth(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.defaultDisplay.getRealSize(point)
            } else {
                wm.defaultDisplay.getSize(point)
            }
            return point.x
        }

        /**
         * 获取屏幕高度
         *
         * @param context Context
         * @return 屏幕高度（px）
         */
        fun getScreenHeight(context: Context): Int {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val point = Point()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.defaultDisplay.getRealSize(point)
            } else {
                wm.defaultDisplay.getSize(point)
            }
            return point.y
        }

        /**
         * 获取dp值
         * */
        fun getDp(context: Context, dp: Int): Int {
            val pxValue2: Float = context.resources.getDimension(dp) //获取对应资源文件下的dp值
            val dpValue: Int = ConvertUtils.px2dp(context, pxValue2) //将px值转换成dp值
            return dpValue
        }

        /**
         * 获取sp值
         * */
        fun getSp(context: Context, sp: Int): Int {
            val pxValue: Float = context.resources.getDimension(sp) //获取对应资源文件下的sp值
            val spValue = ConvertUtils.px2sp(context, pxValue) //将px值转换成sp值
            return spValue

        }
    }
}