package com.android.datafree.appforlive.keeplive.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log

class KeepReceiver : BroadcastReceiver() {
    companion object {
        private val TAG: String = "KeepReceiver"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val action: String? = intent?.action
        action?.let { Log.e(TAG, it) }
        if (TextUtils.equals(action,Intent.ACTION_SCREEN_OFF)){
            //关闭屏幕时，开启1像素activity
            context?.let { KeepManager.getInstance().startKeep(it) }
        }else if (TextUtils.equals(action,Intent.ACTION_SCREEN_ON)){
            //打开屏幕时，关闭1像素activity
            KeepManager.getInstance().finishKeep()
        }
    }
}