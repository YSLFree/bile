package com.android.datafree.bile.entity

import com.android.datafree.bile.R
import com.android.datafree.item.ItemData
import kotlinx.android.synthetic.main.activity_home.*

class HomeItemData {
    fun createItemsData(): MutableList<ItemData> {
        val list: MutableList<ItemData> = mutableListOf()
        val itemData: ItemData = ItemData()
        itemData.txt = "首页"
        itemData.idRes = R.mipmap.icon_home_home
        list.add(itemData)
        val itemData1: ItemData = ItemData()
        itemData1.txt = "附近"
        itemData1.idRes = R.mipmap.icon_home_nearby
        list.add(itemData1)
        return list
    }
}