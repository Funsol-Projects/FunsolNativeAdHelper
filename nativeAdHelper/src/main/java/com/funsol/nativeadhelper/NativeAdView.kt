package com.funsol.nativeadhelper

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout

class NativeAdView(context: Context, attr: AttributeSet) : ConstraintLayout(context, attr) {

    val adPlaceHolder: ConstraintLayout by lazy {
        findViewById(R.id.parent_native_container)
    }

    val adFrame: FrameLayout by lazy {
        findViewById(R.id.admob_native_container)
    }

    init {
        inflate(context, R.layout.dummy_container, this)
    }
}