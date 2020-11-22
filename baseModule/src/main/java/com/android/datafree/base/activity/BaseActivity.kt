package com.android.datafree.base.activity

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.android.datafree.tools.phone.PhoneSystemUtils
import java.lang.reflect.Field
import java.lang.reflect.Method


open class BaseActivity : BaseActivityImpl() {

    override fun setLayout(): Int {
        return super.setLayout()
    }

    override fun activityInit() {
        super.activityInit()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * 全屏显示
     */
    open fun openFullScreenModel() {
        if (Build.VERSION.SDK_INT >= 28) {
            val lp = window.attributes
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }
        val uiOptions: Int = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
        window.decorView.systemUiVisibility = uiOptions
    }

    /**
     * 沉浸
     */
    open fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.getDecorView()
                .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(Color.TRANSPARENT)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 设置Android状态栏的字体颜色，状态栏为亮色的时候字体和图标是黑色，状态栏为暗色的时候字体和图标为白色
     *
     * @param dark 状态栏字体和图标是否为深色,true深色，false浅色
     */
    open fun setStatusBarFontDark(activity: Activity, dark: Boolean) {
        // 小米MIUI

        // android6.0+系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor: View = activity.window.decorView
            if (dark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            }
        } else if (PhoneSystemUtils.isMiui()) {
            try {
                val clazz: Class<*> = activity.window.javaClass
                val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field: Field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                val darkModeFlag: Int = field.getInt(layoutParams)
                val extraFlagField: Method = clazz.getMethod(
                    "setExtraFlags",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                if (dark) {    //状态栏亮色且黑色字体
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag)
                } else {       //清除黑色字体
                    extraFlagField.invoke(window, 0, darkModeFlag)
                }
            } catch (e: Exception) {
                Log.i("EEEEEEE", e.toString())
                //e.printStackTrace();
            }
        } else if (PhoneSystemUtils.isFlyme()) {
            // 魅族FlymeUI
            try {
                val lp: WindowManager.LayoutParams = window.getAttributes()
                val darkFlag: Field =
                    WindowManager.LayoutParams::class.java.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                val meizuFlags: Field =
                    WindowManager.LayoutParams::class.java.getDeclaredField("meizuFlags")
                darkFlag.setAccessible(true)
                meizuFlags.setAccessible(true)
                val bit: Int = darkFlag.getInt(null)
                var value: Int = meizuFlags.getInt(lp)
                value = if (dark) {
                    value or bit
                } else {
                    value and bit.inv()
                }
                meizuFlags.setInt(lp, value)
                window.setAttributes(lp)
            } catch (e: Exception) {
                //  e.printStackTrace();
            }
        }
    }

    /**
     * 在[Activity.setContentView]之后调用
     *
     * @param activity       要实现的沉浸式状态栏的Activity
     * @param titleViewGroup 头部控件的ViewGroup,若为null,整个界面将和状态栏重叠
     */
    open fun initAfterSetContentView(titleViewGroup: View?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        if (titleViewGroup == null) return
        // 设置头部控件ViewGroup的PaddingTop,防止界面与状态栏重叠
        val statusBarHeight = getStatusBarHeight()
        titleViewGroup.setPadding(0, statusBarHeight, 0, 0)
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    open fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId: Int = getResources().getIdentifier(
            "status_bar_height", "dimen", "android"
        )
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId)
        }
        return result
    }

}