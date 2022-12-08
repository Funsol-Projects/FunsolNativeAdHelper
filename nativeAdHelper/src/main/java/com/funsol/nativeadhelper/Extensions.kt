package com.funsol.nativeadhelper

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import androidx.core.view.updateLayoutParams
import com.intuit.sdp.R

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}


fun View.modifyAdPlaceHolder(adLayoutId: AdsLayout, containerBackgroundColor: String, containerBackgroundRadius: Float) {
    val shape = GradientDrawable()

    shape.cornerRadius = containerBackgroundRadius

    if (containerBackgroundColor.isNotEmpty()) {
        try {
            shape.setColor(Color.parseColor(containerBackgroundColor))
        } catch (e: Exception) {
            shape.setColor(Color.parseColor("#F3F3F3"))
        } catch (e: java.lang.Exception) {
            shape.setColor(Color.parseColor("#F3F3F3"))
        }
    } else {
        shape.setColor(Color.parseColor("#F3F3F3"))
    }

    background = shape


    when (adLayoutId) {
        AdsLayout.ONE_A -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._170sdp)
            }
        }
        AdsLayout.ONE_B -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._170sdp)
            }
        }
        AdsLayout.TWO_A -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._240sdp)
            }
        }
        AdsLayout.TWO_B -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._240sdp)
            }
        }
        AdsLayout.THREE_A -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._70sdp)
            }
        }
        AdsLayout.THREE_B -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._70sdp)
            }
        }
        AdsLayout.FOUR_A -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._180sdp)
            }
        }
        AdsLayout.FOUR_B -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._180sdp)
            }
        }
        AdsLayout.SEVEN_A -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._105sdp)
            }
        }
        AdsLayout.SEVEN_B -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._105sdp)
            }
        }
        AdsLayout.SEVEN_C -> {
            updateLayoutParams {
                this.height = context.resources.getDimensionPixelSize(R.dimen._105sdp)
            }
        }
        else -> {}
    }
}


fun colorFromInt(): String {
    return ""
}

fun colorFromAttr(): String {
    return ""
}