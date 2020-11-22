package com.android.datafree.bile.activity


import android.view.KeyEvent
import com.android.datafree.appforlive.mobile.MobileInfoUtils
import com.android.datafree.appforlive.savelive.service.MyJobService
import com.android.datafree.bile.R
import com.android.datafree.base.activity.BaseActivity
import com.android.datafree.base.permission.PermissionGroup
import com.android.datafree.base.show.ToastUtil
import kotlin.system.exitProcess


class HomeActivity : BaseActivity() {
    private var mFirstBack: Long = 0
    override fun setLayout(): Int {
        return R.layout.activity_home
    }

    override fun activityInit() {
        super.activityInit()
        val args = checkPermission(PermissionGroup.BL_STORAGE.toList())
        if (args.isNotEmpty()) {
            requestPermission(args)
        }

        MyJobService.startJob(this)
       // startActivity(MobileInfoUtils.getAutostartSettingIntent(this))
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mFirstBack) <= 500) {
                finish();
                exitProcess(0)//立刻推出程序
            }

            ToastUtil.showShort(this, resources.getString(R.string.double_click_exit_program))
            mFirstBack = System.currentTimeMillis()
        }
        return true
    }
}