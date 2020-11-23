package com.android.datafree.bile.entity

import android.content.Context
import com.android.datafree.bile.R
import com.android.datafree.item.ItemData

class HomeItemData {
    fun createItemsData(context: Context): MutableList<ItemData> {
        val list: MutableList<ItemData> = mutableListOf()
        val itemData: ItemData = ItemData()
        itemData.txt = context.resources.getString(R.string.home_page)
        itemData.idResSelcted = R.mipmap.icon_home_home_checked
        itemData.idResUnSelcted = R.mipmap.icon_home_home_unchecked
        itemData.idResousce = R.id.item_view_home_home
        list.add(itemData)

        val itemData1: ItemData = ItemData()
        itemData1.txt = context.resources.getString(R.string.home_nearby)
        itemData1.idResSelcted = R.mipmap.icon_home_nearby_checked
        itemData1.idResUnSelcted = R.mipmap.icon_home_nearby_unchecked
        itemData1.idResousce = R.id.item_view_home_nearby
        list.add(itemData1)

        val itemData2: ItemData = ItemData()
        itemData2.txt = context.resources.getString(R.string.home_messages)
        itemData2.idResSelcted = R.mipmap.icon_home_message_checked
        itemData2.idResUnSelcted = R.mipmap.icon_home_message_unchecked
        itemData2.idResousce = R.id.item_view_home_message
        list.add(itemData2)

        val itemData3: ItemData = ItemData()
        itemData3.txt = context.resources.getString(R.string.home_mine)
        itemData3.idResSelcted = R.mipmap.icon_home_mine_checked
        itemData3.idResUnSelcted = R.mipmap.icon_home_mine_unchecked
        itemData3.idResousce = R.id.item_view_home_mine
        list.add(itemData3)

        return list
    }
}