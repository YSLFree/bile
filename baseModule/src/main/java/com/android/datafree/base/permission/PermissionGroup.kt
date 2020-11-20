package com.android.datafree.base.permission

import android.Manifest

class PermissionGroup<Activity> {
    companion object {
        val BL_PERMISSIONS_REQUEST_CODE: Int = 0x10//权限请求，请求码

        //Storage权限组
        val BL_STORAGE = arrayOf<String>(
            Manifest.permission.READ_EXTERNAL_STORAGE,//允许程序可以读取设备外部存储空间
            Manifest.permission.WRITE_EXTERNAL_STORAGE//允许程序写入外部存储,如SD卡上写文件
        )

        //Phone权限组
        val BL_PHONE = arrayOf<String>(
            Manifest.permission.READ_PHONE_STATE,//允许程序访问电话状态
            Manifest.permission.READ_PHONE_NUMBERS,//允许程//序读取设备的电话号码
            Manifest.permission.ANSWER_PHONE_CALLS,//允许程序接听来电
            Manifest.permission.ADD_VOICEMAIL,//允许程序添加语音邮件系统
            Manifest.permission.ACCEPT_HANDOVER,//允许呼叫应用继续在另一个应用中启动的呼叫
            Manifest.permission.CALL_PHONE,//允许程序从非系统拨号器里拨打电话
            Manifest.permission.READ_CALL_LOG,//允许程序读取通话记录
            Manifest.permission.WRITE_CALL_LOG,//允许程序写入（但是不能读）用户的联系人数据
            Manifest.permission.USE_SIP,//允许程序使用SIP视频服务
            Manifest.permission.PROCESS_OUTGOING_CALLS//允许程序监视，修改或放弃播出电话
        )

        //Location地位组
        val BL_LOCATION = arrayListOf<String>(
            Manifest.permission.ACCESS_BACKGROUND_LOCATION,//允许应用程序在后台访问位置
            Manifest.permission.ACCESS_COARSE_LOCATION,//允许程序通过WiFi或移动基站的方式获取用户错略的经纬度信息
            Manifest.permission.ACCESS_FINE_LOCATION//允许程序通过GPS芯片接收卫星的定位信息
        )
    }

    fun checkPermission(): Boolean {
        return false
    }
}