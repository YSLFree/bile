package com.android.datafree.appforlive.mobile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver :BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        //标准的写法是需要判别Action的类型的
        if (intent?.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            //你想执行的操作
        }
    }
}