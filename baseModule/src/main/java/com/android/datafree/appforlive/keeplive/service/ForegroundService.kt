package com.android.datafree.appforlive.keeplive.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder

class ForegroundService : Service() {

    companion object {
        class InnerService : Service() {
            override fun onBind(intent: Intent?): IBinder? {
                return null
            }

            override fun onCreate() {
                super.onCreate()
                startForeground(1, Notification())
                stopSelf()
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        startForeground(1, Notification())//Noticefication 没有创建，这里用的匿名对象代替，这个在运行时是要报错的，必须创建对象，不能这样操作
        startService(Intent(this,InnerService::class.java))
    }
}