package com.example.deviceinfo.Modules

import android.app.ActivityManager
import android.content.Context
import android.os.BatteryManager
import android.os.Build
import android.os.SystemClock
import androidx.core.content.ContextCompat.getSystemService

class DeviceInfo {

    fun getPrimarySystemData(context: Context): String {
        val osVersion = Build.VERSION.RELEASE
        val sdkInt = Build.VERSION.SDK_INT
        val deviceModel = Build.MODEL
        val deviceManufacturer = Build.MANUFACTURER

        val batteryManager = getSystemService(context, BatteryManager::class.java) as BatteryManager
        val batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

        return "OS version: $osVersion\n SDK: $sdkInt\n Device model: $deviceModel\n Device manufacturer: $deviceManufacturer\n Battery level: $batteryLevel%"
    }

    fun getMemoryData(context: Context): String {
        val activityManager = getSystemService(context, ActivityManager::class.java) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        val totalMemory = memoryInfo.totalMem / (1024 * 1024)
        val availableMemory = memoryInfo.availMem / (1024 * 1024)
        val isLowMemory = memoryInfo.lowMemory

        val lowMemoryMode = if (isLowMemory) {
            "activated"
        } else {
            "not activated"
        }

        return "Total memory: $totalMemory mb\n Available memory: $availableMemory mb\n Low memory mode: $lowMemoryMode"
    }
}