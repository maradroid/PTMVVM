package hr.factory.base.utils

import android.content.Context
import android.content.pm.PackageManager


const val CHROME_PACKAGE = "com.android.chrome"

fun isChromeInstalled(context: Context): Boolean {
    return try {
        val info = context.packageManager.getApplicationInfo(CHROME_PACKAGE, 0)
        info.enabled
    } catch (e: PackageManager.NameNotFoundException){
        false
    }
}