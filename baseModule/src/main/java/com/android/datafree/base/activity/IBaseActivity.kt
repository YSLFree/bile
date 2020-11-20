package com.android.datafree.base.activity


interface IBaseActivity {
    //在设置界面之前调用，设置某些参数
    fun beforeSetView()

    //设置是否允许应用截屏
    fun setScreenShot(): Boolean

    //初始化
    fun activityInit()

    //设置界面layout
    fun setLayout(): Int

    //现实错误信息
    fun showErrorMsg()

    //现实警告信息
    fun showWarnMsg()

    //现实普通消息
    fun showCustomMsg()

    //现实进度
    fun showProgress()

    //显示Dialog
    fun showDialog()

    //显示PopupWindow
    fun showPopupWindos()

    //获取数据
    fun getData(): Any?

    //刷新界面
    fun refreshView(): Any?

    //权限检查
    fun checkPermission(args: List<String>): List<String>

    //权限请求
    fun requestPermission(args: List<String>)

}