package com.android.datafree.appforlive.keeplive.activity

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class KeepActivity : AppCompatActivity() {
    private val TAG: String = "KeepActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "启动KeepActivity")
        //像素点放在左上角
        window.setGravity(Gravity.START or Gravity.TOP)
        val params: WindowManager.LayoutParams = window.attributes
        //设宽高
        params.width = 1
        params.height = 1
        //设置起始坐标
        params.x = 0
        params.y = 0
        window.attributes = params
        //KeepActivity 创建一个弱引用
        KeepManager.getInstance().setKeep(this)
    }
}