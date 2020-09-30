package com.astronout.androidtestdot.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.astronout.androidtestdot.BuildConfig
import com.astronout.androidtestdot.widget.NoInternetConnectionDialog

fun visible() = View.VISIBLE
fun invisible() = View.INVISIBLE
fun gone() = View.GONE

fun logDebug(message: String) {
    if (BuildConfig.DEBUG)
        Log.d(Constants.TAG_DEBUG, message)
}

fun logError( message: String, throwable : Throwable? = null) {
    if (BuildConfig.DEBUG) {
        Log.e(Constants.TAG_ERROR, message)
    }
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.isInternetAvailable(): Boolean {
    var result = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }
        }
    }

    return result
}

fun Context.noInternetConnectionAlert(onActionOK : () -> Unit) {
    val noInternetDialog = NoInternetConnectionDialog(this) {
        onActionOK()
    }
    noInternetDialog.setCancelable(false)
    noInternetDialog.setCanceledOnTouchOutside(false)
    noInternetDialog.show()
    noInternetDialog.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
}