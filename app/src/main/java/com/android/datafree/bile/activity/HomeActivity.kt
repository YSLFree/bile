package com.android.datafree.bile.activity


import android.util.Log
import android.view.KeyEvent
import com.android.datafree.appforlive.savelive.service.MyJobService
import com.android.datafree.base.activity.BaseActivity
import com.android.datafree.base.permission.PermissionGroup
import com.android.datafree.base.show.ToastUtil
import com.android.datafree.bile.R
import com.android.datafree.bile.entity.HomeItemData
import com.android.datafree.item.ItemData
import com.android.datafree.item.ItemViewSingleClickListener
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.system.exitProcess


class HomeActivity : BaseActivity() {
    private var mFirstBack: Long = 0
    private lateinit var mItemDatas: MutableList<ItemData>
    override fun setLayout(): Int {
        return R.layout.activity_home
    }

    override fun activityInit() {
        super.activityInit()
        TAG="HomeActivity"
        val args = checkPermission(PermissionGroup.BL_STORAGE.toList())
        if (args.isNotEmpty()) {
            requestPermission(args)
        }
        mItemDatas = HomeItemData().createItemsData(this)
        item_view_group.setDataSource(mItemDatas)
        item_view_group.setOnSingleClickListener(object : ItemViewSingleClickListener {
            override fun onSingleClick(viewId: Int) {
                mItemDatas.forEach {
                    it.isSelected = viewId==it.idResousce
                }
                item_view_group.refreshViewItem()
                Log.i(TAG, "onSingleClick-id=" + viewId)
            }
        })
        item_view_group.createItemGroupLinear()
        item_view_group.select(0)
        MyJobService.startJob(this)
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