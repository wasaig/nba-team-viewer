package com.score.app.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log

class NetworkUtil {

    companion object {

        const val TAG = "NetworkUtil"

        var isConnected = false

        fun registerNetworkCallback(context: Context) {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val request = NetworkRequest.Builder()
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    .addCapability(NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED)
                    .build()
            cm.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Log.d(TAG, "onAvailable")
                    isConnected = true
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    Log.d(TAG, "onLost")
                    isConnected = false
                }

                override fun onUnavailable() {
                    Log.d(TAG, "onUnavailable")
                    isConnected = false
                }
            })
        }
    }
}