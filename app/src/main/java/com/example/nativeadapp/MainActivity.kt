package com.example.nativeadapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nativeadapp.databinding.ActivityMainBinding
import com.funsol.nativeadhelper.AdConfigurations
import com.funsol.nativeadhelper.AdsLayout
import com.funsol.nativeadhelper.NativeAdHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer1.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer1.adFrame,
                adLayout = AdsLayout.ONE_A,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer2.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer2.adFrame,
                adLayout = AdsLayout.ONE_B,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer3.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer3.adFrame,
                adLayout = AdsLayout.TWO_A,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer4.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer4.adFrame,
                adLayout = AdsLayout.TWO_B,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer5.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer5.adFrame,
                adLayout = AdsLayout.THREE_A,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer6.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer6.adFrame,
                adLayout = AdsLayout.THREE_B,
                CTARoundness = 0f,
                CTABackgroundColor = "#95831F"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer7.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer7.adFrame,
                adLayout = AdsLayout.FOUR_A,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer8.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer8.adFrame,
                adLayout = AdsLayout.FOUR_B,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F",
                iconCornerRadius = 1f
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer9.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer9.adFrame,
                adLayout = AdsLayout.SEVEN_A,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer10.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer10.adFrame,
                adLayout = AdsLayout.SEVEN_B,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F",
                titleFontColor = "#5D5524"
            )
        )

        NativeAdHelper(
            this, AdConfigurations(
                nativeContainer = binding.nativeAdContainer11.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer11.adFrame,
                adLayout = AdsLayout.SEVEN_C,
                CTARoundness = 1f,
                CTABackgroundColor = "#95831F",
                bodyFontColor = "#2F2D24"
            )
        )

    }

}