package com.android.datafree.appforlive.mobile

import android.content.ComponentName
import android.content.Context

import android.content.Intent
import android.net.Uri

import android.os.Build
import java.util.*

//跳转到自启动界面
class MobileInfoUtils {
    companion object {
        /**
         * 获取自启动管理页面的Intent
         * @param context context
         * @return 返回自启动管理页面的Intent
         */
        fun getAutostartSettingIntent(context: Context): Intent? {
            var componentName: ComponentName? = null
            val brand = Build.MANUFACTURER
            val intent = Intent()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            when (brand.toLowerCase(Locale.getDefault())) {
                "samsung" -> componentName = ComponentName(
                    "com.samsung.android.sm",
                    "com.samsung.android.sm.app.dashboard.SmartManagerDashBoardActivity"
                )
                "huawei" ->             //荣耀V8，EMUI 8.0.0，Android 8.0上，以下两者效果一样
                    componentName = ComponentName(
                        "com.huawei.systemmanager",
                        "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"
                    )
                "xiaomi" -> componentName = ComponentName(
                    "com.miui.securitycenter",
                    "com.miui.permcenter.autostart.AutoStartManagementActivity"
                )
                "vivo" -> //            componentName = new ComponentName("com.iqoo.secure", "com.iqoo.secure.safaguard.PurviewTabActivity");
                    componentName = ComponentName(
                        "com.iqoo.secure",
                        "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity"
                    )
                "oppo" -> //            componentName = new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity");
                    componentName = ComponentName(
                        "com.coloros.oppoguardelf",
                        "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity"
                    )
                "yulong", "360" -> componentName = ComponentName(
                    "com.yulong.android.coolsafe",
                    "com.yulong.android.coolsafe.ui.activity.autorun.AutoRunListActivity"
                )
                "meizu" -> componentName =
                    ComponentName("com.meizu.safe", "com.meizu.safe.permission.SmartBGActivity")
                "oneplus" -> componentName = ComponentName(
                    "com.oneplus.security",
                    "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity"
                )
                "letv" -> {
                    intent.action = "com.letv.android.permissionautoboot"
                    intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                    intent.data = Uri.fromParts("package", context.getPackageName(), null)
                }
                else -> {
                    intent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
                    intent.data = Uri.fromParts("package", context.getPackageName(), null)
                }
            }
            intent.component = componentName
            return intent
        }
    }
}