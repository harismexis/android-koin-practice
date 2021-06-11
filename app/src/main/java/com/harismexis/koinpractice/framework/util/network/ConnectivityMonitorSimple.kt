package com.harismexis.koinpractice.framework.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class ConnectivityMonitorSimple(private val appContext: Context) {

    fun isOnline(): Boolean {
        val connMgr = appContext.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}