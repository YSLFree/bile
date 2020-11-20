package com.android.datafree.base.activity

import android.app.Activity
import java.lang.Exception


class ActivityCollector {

    companion object {
        private var mActivitys: MutableList<Activity> = ArrayList()

        /**
         * 向List中添加一个活动
         *
         * @param activity 活动
         */
        fun addActivity(activity: Activity) {
            mActivitys.add(activity)
        }

        /**
         * 根据类名获取某个具体的activity
         *
         * @param clazz Activity 类名
         */
        fun getActivity(clazz: Class<out Activity>): Activity? {
            for (activity in mActivitys) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (activity::class.java.simpleName == clazz::class.java.simpleName && !activity.isDestroyed)
                        return activity
                }
                return activity
            }
            return null
        }

        /**
         * 从List中移除活动
         *
         * @param activity 活动
         */
        fun removeActivity(activity: Activity) {
            mActivitys.remove(activity)
        }

        /**
         * 将List中存储的活动全部销毁掉
         */
        fun finishAll() {
            for (activity in mActivitys) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    if (!activity.isFinishing && !activity.isDestroyed) {
                        try {
                            activity.finish()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
    }
}