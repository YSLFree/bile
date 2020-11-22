package com.android.datafree.item

import android.content.Context
import android.os.Parcel
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.android.datafree.base.screen.ScreenUtils
import com.android.datafree.basemodule.R

class ItemView : RelativeLayout, Item {
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

    private lateinit var imageView: AppCompatImageView
    private lateinit var textView: TextView
    private lateinit var imagePrarms: RelativeLayout.LayoutParams
    private lateinit var textPrarms: RelativeLayout.LayoutParams

    fun createItem(itemData: ItemData) {
        imageView = AppCompatImageView(mContext)
        textView = AppCompatTextView(mContext)
        textPrarms = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textPrarms.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE)
        textPrarms.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE)
        textView.id = R.id.item_view_textview  //一定不要忘记设置id，否则下面用到的时候id获取不到
        this.addView(textView, textPrarms)//添加文字
        textView.text = itemData.txt
        textView.gravity = Gravity.CENTER
        imagePrarms = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        //imagePrarms.width=ScreenUtils.getDp(mContext,)
        var isd = textView.id
        imagePrarms.addRule(RelativeLayout.ABOVE, textView.id)
        imagePrarms.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE)
        this.addView(imageView, imagePrarms)//添加图标
        imageView.setImageResource(itemData.idRes)
    }
}