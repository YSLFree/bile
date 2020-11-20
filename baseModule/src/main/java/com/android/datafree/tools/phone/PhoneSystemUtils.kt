package com.android.datafree.tools.phone

import android.os.Build

import android.text.TextUtils
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*


class PhoneSystemUtils {
    companion object {
        private val TAG = "Rom"

        val ROM_MIUI = "MIUI" //小米

        val ROM_EMUI = "EMUI" //化为

        val ROM_FLYME = "FLYME" //魅族

        val ROM_OPPO = "OPPO" //oppo

        val ROM_SMARTISAN = "SMARTISAN" //三星

        val ROM_VIVO = "VIVO" //vivo

        val ROM_QIKU = "QIKU" //360


        private val KEY_VERSION_MIUI = "ro.miui.ui.version.name"
        private val KEY_VERSION_EMUI = "ro.build.version.emui"
        private val KEY_VERSION_OPPO = "ro.build.version.opporom"
        private val KEY_VERSION_SMARTISAN = "ro.smartisan.version"
        private val KEY_VERSION_VIVO = "ro.vivo.os.version"

        private var sName: String? = null
        private var sVersion: String? = null

        fun isEmui(): Boolean {
            return check(ROM_EMUI)
        }

        fun isMiui(): Boolean {
            return check(ROM_MIUI)
        }

        fun isVivo(): Boolean {
            return check(ROM_VIVO)
        }

        fun isOppo(): Boolean {
            return check(ROM_OPPO)
        }

        fun isFlyme(): Boolean {
            return check(ROM_FLYME)
        }

        fun is360(): Boolean {
            return check(ROM_QIKU) || check("360")
        }

        fun isSmartisan(): Boolean {
            return check(ROM_SMARTISAN)
        }

        fun getName(): String? {
            if (sName == null) {
                check("")
            }
            return sName
        }

        fun getVersion(): String? {
            if (sVersion == null) {
                check("")
            }
            return sVersion
        }

        private fun check(rom: String): Boolean {
            if (sName != null) {
                return sName == rom
            }
            if (!TextUtils.isEmpty(getProp(KEY_VERSION_MIUI).also { sVersion = it })) {
                sName = ROM_MIUI
            } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_EMUI).also { sVersion = it })) {
                sName = ROM_EMUI
            } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_OPPO).also { sVersion = it })) {
                sName = ROM_OPPO
            } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_VIVO).also { sVersion = it })) {
                sName = ROM_VIVO
            } else if (!TextUtils.isEmpty(getProp(KEY_VERSION_SMARTISAN).also { sVersion = it })) {
                sName = ROM_SMARTISAN
            } else {
                sVersion = Build.DISPLAY
                if (sVersion?.toUpperCase(Locale.getDefault())?.contains(ROM_FLYME)!!) {
                    sName = ROM_FLYME
                } else {
                    sVersion = Build.UNKNOWN
                    sName = Build.MANUFACTURER.toUpperCase(Locale.getDefault())
                }
            }
            return sName == rom
        }

        fun getProp(name: String): String? {
            var line: String? = null
            var input: BufferedReader? = null
            try {
                val p = Runtime.getRuntime().exec("getprop $name")
                input = BufferedReader(InputStreamReader(p.inputStream), 1024)
                line = input.readLine()
                input.close()
            } catch (ex: IOException) {
                Log.e(TAG, "Unable to read prop $name", ex)
                return null
            } finally {
                if (input != null) {
                    try {
                        input.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            return line
        }
    }
}