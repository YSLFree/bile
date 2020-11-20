package com.android.datafree.base.permission

interface IPermissionBack {
    //权限同意
    fun onGranted(pers: Array<out String>)

    //权限拒绝
    fun onDenied(pers: Array<out String>)
}