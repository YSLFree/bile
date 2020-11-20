package com.android.datafree.bile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.datafree.bile.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var dataModel: InfoModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataModel = InfoModel()
        var dataing: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataing.dataModel = dataModel
        refreshInfo("")
    }

    fun refreshInfo(n: String): Unit {
        dataModel.age = 10
        dataModel.name = "Gloa"
        dataModel.phone = "112445544"
    }
}