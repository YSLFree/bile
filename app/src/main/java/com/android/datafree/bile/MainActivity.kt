package com.android.datafree.bile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.datafree.base.entity.InfoModel
import com.android.datafree.bile.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var dataModel_: InfoModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataModel_ = InfoModel()
        var dataing: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataing.dataModel = dataModel_
        refreshInfo("")
    }

    fun refreshInfo(n: String): Unit {
        dataModel_.age = 10
        dataModel_.name = "Gloa"
        dataModel_.phone = "112445544"
    }
}