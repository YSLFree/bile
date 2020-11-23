package com.android.datafree.item

import androidx.annotation.IdRes

class ItemData {
    var txt: String = ""//item文字
    var idResSelcted: Int = 0//被选中图片显示
    var idResUnSelcted: Int = 0//未选中图片显示

    @IdRes
    var idResousce = 0//view Id
    var isSelected = false//是否被选中
}