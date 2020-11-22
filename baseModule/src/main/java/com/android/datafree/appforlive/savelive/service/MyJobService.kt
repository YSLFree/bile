package com.android.datafree.appforlive.savelive.service

import android.annotation.SuppressLint
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.util.Log

@SuppressLint("NewApi")
class MyJobService : JobService() {
    val TAG = "MyJobService"

    //JobService 特点是，每隔一段时间就会去启动一下Service
    companion object {
        private val JOB_ID: Int = 8
        fun startJob(context: Context) {
            val jobScheduler: JobScheduler =
                context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
            val builder: JobInfo.Builder = JobInfo.Builder(
                JOB_ID,
                ComponentName(context.packageName, MyJobService::class.java.name)
            ).setPersisted(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //6.0以后，延迟加载而且是单次执行下面的延时最小值就是5000，所以设置1000也是5000，只有大于5000才有效，所以一般默认为5000
                builder.setMinimumLatency(5000)
            } else {//6.0及6.0以下，可以通过设置周期执行，周期时间可以自己设置
                builder.setPeriodic(5000)
            }
            jobScheduler.schedule(builder.build())
        }
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.e(TAG, "onStartJob")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            startJob(this)//7.0及7.0因为只启动一次，所以以后需要自己启动自己
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}