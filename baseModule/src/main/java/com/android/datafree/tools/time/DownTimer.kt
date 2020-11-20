package com.android.datafree.tools.time

import android.os.CountDownTimer
import com.android.datafree.tools.thread.runOnUi

class DownTimer(var totalTime: Long, var countTime: Long, var iTimer: IDownTimer) :
    CountDownTimer(totalTime, countTime) {

    override fun onTick(millisUntilFinished: Long) {//millisUntilFinished,单位：毫秒
        runOnUi { iTimer.onCount(millisUntilFinished) }

    }

    override fun onFinish() {
        runOnUi { iTimer.onFinish() }
    }

}