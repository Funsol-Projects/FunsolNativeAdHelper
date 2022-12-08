package com.funsol.nativeadhelper

import android.widget.FrameLayout
import androidx.annotation.FloatRange
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Class @AdConfiguration is responsible for setting layouts and modification of Ad Content.
 *
 * @param nativeContainer This act as a placeholder, responsible for showing shimmer effect while the AD is loading.
 *
 * @param adMobContainer This act as a placeholder, responsible for showing shimmer effect while the AD is loading.
 */

data class AdConfigurations(
    var nativeContainer: ConstraintLayout,
    var adMobContainer: FrameLayout,
    var adLayout: AdsLayout = AdsLayout.SEVEN_A,
    var adId: String = "ca-app-pub-3940256099942544/2247696110",
    var showAD: Boolean = true,
    var customLayout: Int? = null,
    var containerColor: String = "#F3F3F3",
    @FloatRange(from = 0.0, to = 50.0) var containerRadius: Float = 25f,
    var titleFontColor: String = "#000000",
    @FloatRange(from = 11.0, to = 17.0) var titleFontSize: Float = 14F,
    var bodyFontColor: String = "#000000",
    @FloatRange(from = 10.0, to = 15.0) var bodyFontSize: Float = 12F,
    @FloatRange(from = 0.0, to = 1.0) var CTARoundness: Float = 0.5f,
    var CTABackgroundColor: String = "#E92222",
    @FloatRange(from = 12.0, to = 17.0) var CTAFontSize: Float = 14F,
    var CTAFontColor: String = "#FFFFFF",
    @FloatRange(from = 0.0, to = 30.0) var attrRadius: Float = 20f,
    var attrBackgroundColor: String = "#FF9800",
    @FloatRange(from = 10.0, to = 15.0) var attrFontSize: Float = 12F,
    var attrFontColor: String = "#FFFFFF",
    @FloatRange(from = 0.0, to = 1.0) var iconCornerRadius: Float = 0.2f,
    var iconBackGroundColor: String = "F3F3F3",
)