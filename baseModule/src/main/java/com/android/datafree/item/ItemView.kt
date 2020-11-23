package com.android.datafree.item

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.android.datafree.base.screen.ScreenUtils
import com.android.datafree.basemodule.R

class ItemView : RelativeLayout {
    private lateinit var mContext: Context
    private var mItemData: ItemData? = null
    private var mListener: ItemViewSingleClickListener? = null

    @IdRes
    var mViewId: Int = -1

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
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textPrarms.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE)
        textPrarms.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, TRUE)
        textView.id = R.id.item_view_textview  //一定不要忘记设置id，否则下面用到的时候id获取不到
        this.addView(textView, textPrarms)//添加文字
        textView.text = itemData.txt
        textView.textSize = ScreenUtils.getSp(context, R.dimen.sp_10).toFloat()
        textView.gravity = Gravity.CENTER
        imagePrarms = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        var margin = ScreenUtils.getDp(mContext,R.dimen.dp_5)
        imagePrarms.addRule(RelativeLayout.ABOVE, textView.id)
        imagePrarms.addRule(RelativeLayout.CENTER_HORIZONTAL, TRUE)

        imageView.setPadding(margin,margin,margin,margin)
        this.addView(imageView, imagePrarms)//添加图标
        imageView.setImageResource(itemData.idResUnSelcted)
        imageView.scaleType= ImageView.ScaleType.FIT_CENTER
        mViewId = itemData.idResousce
        mItemData = itemData
        setOnClickListener {
            refreshItemView()
            mListener?.onSingleClick(mViewId)
        }
    }

    /*
    * 单击事件
    * */
    fun setOnSingleClickListener(listener: ItemViewSingleClickListener?) {
        mListener = listener

    }

    fun refreshItemView() {
        if (mItemData?.isSelected!!) {
            mItemData?.idResSelcted?.let { imageView.setImageResource(it) }
            textView.setTextColor(resources.getColor(R.color.main_checked_color))
        } else {
            mItemData?.idResUnSelcted?.let { imageView.setImageResource(it) }
            textView.setTextColor(resources.getColor(R.color.main_unchecked_color))
        }
    }


}