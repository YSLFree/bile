package com.android.datafree.item

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

// 水平布局
class ItemViewGroupLinear : LinearLayout {
    private lateinit var mContext: Context

    constructor(contxt: Context) : super(contxt) {
        mContext = context
    }

    constructor(contxt: Context, attrs: AttributeSet) : super(contxt, attrs) {
        mContext = context
    }

    constructor(contxt: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        contxt,
        attrs,
        defStyleAttr
    ) {
        mContext = context
    }

    fun createItemGroupLinear(listData: List<ItemData>) {
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        params.weight = 1F
        listData.forEach {
            val itemView: ItemView = ItemView(mContext)
            itemView.createItem(it)
            addView(itemView, params)
        }
    }
}