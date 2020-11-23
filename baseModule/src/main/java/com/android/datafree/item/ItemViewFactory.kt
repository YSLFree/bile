package com.android.datafree.item

import android.content.Context

class ItemViewFactory() {
    companion object {
        private var mInsance: ItemViewFactory? = null

        fun getInstance(): ItemViewFactory {
            if (mInsance == null) {
                mInsance = ItemViewFactory()
            }
            return ItemViewFactory()
        }
    }

    fun createSingleItem(context: Context, itemData: ItemData): ItemView {
        val itemView: ItemView = ItemView(context)
        itemView.createItem(itemData)
        return itemView
    }

    fun createItemViewGroupLinear(contxt: Context, list: List<ItemData>): ItemViewGroupLinear {
        val itemViewGroupLinear = ItemViewGroupLinear(contxt)
        return itemViewGroupLinear
    }
}