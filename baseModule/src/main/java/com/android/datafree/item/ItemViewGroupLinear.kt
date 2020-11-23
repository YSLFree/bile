package com.android.datafree.item

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

// 水平布局
class ItemViewGroupLinear : LinearLayout {
    private lateinit var mContext: Context
    private var mListener: ItemViewSingleClickListener? = null
    var mListData: MutableList<ItemData>? = null
    var mListView: MutableList<ItemView>? = null

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

    fun setDataSource(listData: MutableList<ItemData>) {
        mListData = listData
    }

    fun createItemGroupLinear() {
        mListView = mutableListOf()
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        params.weight = 1F
        mListData?.forEach {
            val itemView: ItemView = ItemView(mContext)
            itemView.createItem(it)
            itemView.id = it.idResousce
            itemView.setOnSingleClickListener(mListener)
            mListView?.add(itemView)
            addView(itemView, params)
        }
    }

    fun select(postion: Int) {
        if (postion >= mListData?.size!!) {
            return
        }
        mListData?.forEach {
            it.isSelected = false
        }
        mListData?.get(postion)?.isSelected = true
        refreshViewItem()
    }

    fun setOnSingleClickListener(listener: ItemViewSingleClickListener?) {
        mListener = listener
    }

    fun refreshViewItem() {
        mListView?.forEach {
            it.refreshItemView()
        }
    }
}