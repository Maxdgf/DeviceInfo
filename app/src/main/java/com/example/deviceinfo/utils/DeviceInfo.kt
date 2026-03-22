package com.example.deviceinfo.utils

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService

class DeviceInfo(private val context: Context) {
    /**
     * Returns device system data.
     * @return data string, which contains: `OS version`, `SDK`, `Device model`, `Device manufacturer`
     */
    fun getPrimarySystemData(): String {
        val osVersion = Build.VERSION.RELEASE
        val sdkInt = Build.VERSION.SDK_INT
        val deviceModel = Build.MODEL
        val deviceManufacturer = Build.MANUFACTURER

        return "OS version: $osVersion\n SDK: $sdkInt\n Device model: $deviceModel\n Device manufacturer: $deviceManufacturer\n"
    }

    /**
     * Returns device memory data.
     * @return data string, which contains `Total RAM memory`, `Low memory mode` state*/
    fun getMemoryData(): String {
        val activityManager = getSystemService(context, ActivityManager::class.java) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        val totalMemory = memoryInfo.totalMem / (1024 * 1024)
        val isLowMemory = memoryInfo.lowMemory

        val lowMemoryMode =
            if (isLowMemory) "activated"
            else "not activated"

        return "Total RAM memory: $totalMemory mb\n mb\n Low memory mode: $lowMemoryMode"
    }
}