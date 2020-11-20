package com.android.datafree.bile.activity

import android.content.Intent
import android.view.KeyEvent
import com.android.datafree.base.activity.BaseActivity
import com.android.datafree.bile.R
import com.android.datafree.bile.R.string.sec
import com.android.datafree.tools.time.DownTimer
import com.android.datafree.tools.time.IDownTimer
import com.android.datafree.tools.time.TimerFlag
import com.android.datafree.tools.time.TimerUtil
import kotlinx.android.synthetic.main.activity_splash.*
import kotlin.system.exitProcess

class SplashActivity : BaseActivity() {
    override fun setLayout(): Int {
        return R.layout.activity_splash
    }


    override fun activityInit() {
        super.activityInit()
        openFullScreenModel()
        var time: Int = 6
        TimerUtil.getInstance()?.startTimerTask(
            TimerFlag.FLAG_SPLASH,
            DownTimer(6000, 1000, object : IDownTimer {
                override fun onCount(countTime: Long) {
                    --time
                    val value = time.toString() + "｜" + resources.getString(R.string.turn_this_)
                    tvCountTimer.text = value
                }

                override fun onFinish() {
                    val intent: Intent = Intent()
                    intent.setClass(this@SplashActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            })
        )
        tvCountTimer.setOnClickListener {
            TimerUtil.getInstance()?.stopTimerTask(TimerFlag.FLAG_SPLASH)
            val intent: Intent = Intent()
            intent.setClass(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            TimerUtil.getInstance()?.stopTimerTask(TimerFlag.FLAG_SPLASH)
            finish()
            exitProcess(0)//立刻推出程序
        }
        return true
    }
}