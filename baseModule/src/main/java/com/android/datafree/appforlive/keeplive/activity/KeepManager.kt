package com.android.datafree.appforlive.keeplive.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import java.lang.ref.WeakReference

class KeepManager() {

    companion object {

        private val mInstance: KeepManager = KeepManager()

        fun getInstance(): KeepManager {
            return mInstance
        }
    }

    //广播
    private var mKeepRecevier: KeepReceiver? = null

    private var mKeepActivity: WeakReference<Activity>? = null

    /*
    * 注册 开屏和锁屏的 广播
    * */
    fun registerKeep(context: Context) {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        mKeepRecevier = KeepReceiver()
        context.registerReceiver(mKeepRecevier, filter)
    }

    /*
    * 注销广播接收者
    * */
    fun unregisterKeep(context: Context) {
        if (mKeepRecevier != null) {
            context.unregisterReceiver(mKeepRecevier)
        }
    }

    /*
    * 开启1像素Activity
    * */
    fun startKeep(context: Context) {
        val intent = Intent(context, KeepActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    /*
        * 关闭1像素Activity
        * */
    fun finishKeep() {
        if (mKeepActivity != null) {
            val activity: Activity? = mKeepActivity!!.get()
            activity?.finish()
        }
    }

    /*
    *  设置1像素Activity
    * */
    public fun setKeep(keep: KeepActivity) {
        mKeepActivity = WeakReference<Activity>(keep)
    }
}