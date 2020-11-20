package com.android.datafree.tools.time

class TimerUtil {
    companion object {
        private val timeTaskMaps: MutableMap<Int, DownTimer> = mutableMapOf()
        private var instance: TimerUtil? = null
        fun getInstance(): TimerUtil? {
            if (instance == null) {
                instance = TimerUtil()
            }
            return instance
        }
    }

    fun startTimerTask(timerFlag: Int, downTimer: DownTimer) {
        for ((timerFlag_, downTimer_) in timeTaskMaps) {
            if (timerFlag_ == timerFlag) {
                downTimer_.cancel()//先取消倒计时
                timeTaskMaps.remove(timerFlag_)//再移除倒计时
                timeTaskMaps[timerFlag] = downTimer//重新将倒计时添加进容器
                return
            }
        }
        timeTaskMaps[timerFlag] = downTimer//将倒计时添加进容器
        downTimer.start()
    }

    fun stopTimerTask(timerFlag: Int) {
        for ((timerFlag_, downTimer_) in timeTaskMaps) {
            if (timerFlag_ == timerFlag) {
                downTimer_.cancel()//先取消倒计时
                timeTaskMaps.remove(timerFlag_)//再移除倒计时
                return
            }
        }
    }

}