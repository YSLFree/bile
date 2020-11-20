package com.android.datafree.base.activity

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.android.datafree.base.permission.IPermissionBack
import com.android.datafree.base.permission.PermissionGroup
import com.android.datafree.basemodule.BuildConfig

open class BaseActivityImpl : AppCompatActivity(), IBaseActivity {
    lateinit var TAG: String
    lateinit var mIPermissionBack: IPermissionBack
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = BaseActivityImpl::class.java.kotlin.simpleName.toString()
        beforeSetView()
        setContentView(setLayout())
        ActivityCollector.addActivity(this)
        if (setScreenShot()) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE
            )
        }
        activityInit()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        //activity管理
        ActivityCollector.removeActivity(this)
    }

    override fun beforeSetView() {

    }

    override fun setScreenShot(): Boolean {
        return false//默认可以截屏
    }

    override fun activityInit() {

    }

    override fun setLayout(): Int {
        return 0
    }

    override fun showErrorMsg() {

    }

    override fun showWarnMsg() {

    }

    override fun showCustomMsg() {

    }

    override fun showProgress() {

    }

    override fun showDialog() {

    }

    override fun showPopupWindos() {

    }

    override fun getData(): Any? {
        return null
    }

    override fun refreshView(): Any? {
      return null
    }

    //检查权限
    override fun checkPermission(args: List<String>): List<String> {
        val backPermissionStrLists: MutableList<String> = mutableListOf()
        for (arg in args) {
            //判断是否具有该权限
            if (PackageManager.PERMISSION_GRANTED != packageManager.checkPermission(
                    arg,
                    packageName
                )
            ) {//不具有该权限就加入返回列表里
                backPermissionStrLists.add(arg)
            }
        }
        return backPermissionStrLists
    }

    //请求权限
    override fun requestPermission(args: List<String>) {
        ActivityCompat.requestPermissions(
            this,
            args.toTypedArray(),
            PermissionGroup.BL_PERMISSIONS_REQUEST_CODE
        )
    }

    //权限请求返回
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionGroup.BL_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty()) {
                for (result in grantResults) {
                    if (result == PackageManager.PERMISSION_GRANTED) {

                        if (BuildConfig.DEBUG)
                            Log.i(TAG, "onRequestPermissionsResult granted")

                    } else {
                        if (BuildConfig.DEBUG)
                            Log.i(TAG, "onRequestPermissionsResult denied")
                        showWarnMsg()
                    }
                }
            }

        }
    }
}