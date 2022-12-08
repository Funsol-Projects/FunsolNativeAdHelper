package com.funsol.nativeadhelper

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView
import jp.wasabeef.glide.transformations.BlurTransformation

private const val TAG = "NativeAdHelper"

class NativeAdHelper(private val context: Activity, adConfigurations: AdConfigurations) {

    /* companion object {
         private var adMobNativeAd: NativeAd? = null
     }*/

    init {
        if (adConfigurations.showAD && context.isNetworkAvailable()) {
            getNative(adConfigurations)
        } else {
            adConfigurations.nativeContainer.visibility = View.GONE
        }
    }

    private fun getNative(adConfigurations: AdConfigurations) {
        adConfigurations.nativeContainer.modifyAdPlaceHolder(adConfigurations.adLayout, adConfigurations.containerColor, adConfigurations.containerRadius)

        /*
        if(adMobNativeAd != null){
            adConfigurations.nativeContainer.visibility = View.VISIBLE
            adConfigurations.adMobContainer.visibility = View.VISIBLE

            val privateAD: NativeAd = adMobNativeAd!!
            populateUnifiedNativeAdView(privateAD, adConfigurations)

            adMobNativeAd = null

            return
        }
        */

        AdLoader.Builder(context, adConfigurations.adId)
            .forNativeAd { ad: NativeAd ->
                Log.i(TAG, "New AD")

                if (context.isDestroyed) {
                    ad.destroy()
                    return@forNativeAd
                }

                adConfigurations.nativeContainer.visibility = View.VISIBLE
                adConfigurations.adMobContainer.visibility = View.VISIBLE
                populateUnifiedNativeAdView(ad, adConfigurations)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.i(TAG, "Failed; code: " + adError.code + ", message: " + adError.message)
                    adConfigurations.nativeContainer.visibility = View.GONE
                    adConfigurations.nativeContainer.findViewById<ShimmerFrameLayout>(R.id.native_shimmer).stopShimmer()
                }
            })
            .withNativeAdOptions(NativeAdOptions.Builder().setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT).build())
            .build().loadAd(AdRequest.Builder().build())
    }

    @SuppressLint("InflateParams")
    private fun populateUnifiedNativeAdView(nativeAd: NativeAd, adConfigurations: AdConfigurations) {
        Log.i(TAG, "populateUnifiedNativeAdView: ")
        val textView = adConfigurations.nativeContainer.findViewById<TextView>(R.id.loading_ad)
        val shimmerLayout = adConfigurations.nativeContainer.findViewById<ShimmerFrameLayout>(R.id.native_shimmer)
        shimmerLayout.stopShimmer()
        shimmerLayout.visibility = View.GONE
        if (textView != null) textView.visibility = View.GONE
        val inflater = LayoutInflater.from(context)

        val adView: NativeAdView = when (adConfigurations.adLayout) {
            AdsLayout.CUSTOM -> {
                try {
                    inflater.inflate(adConfigurations.customLayout!!, null) as NativeAdView
                } catch (e: java.lang.Exception) {
                    inflater.inflate(R.layout.admob_native_7_a, null) as NativeAdView
                }
            }

            AdsLayout.ONE_A -> {
                inflater.inflate(R.layout.admob_native_1_a, null) as NativeAdView
            }
            AdsLayout.ONE_B -> {
                inflater.inflate(R.layout.admob_native_1_b, null) as NativeAdView
            }
            AdsLayout.TWO_A -> {
                inflater.inflate(R.layout.admob_native_2_a, null) as NativeAdView
            }
            AdsLayout.TWO_B -> {
                inflater.inflate(R.layout.admob_native_2_b, null) as NativeAdView
            }
            AdsLayout.THREE_A -> {
                inflater.inflate(R.layout.admob_native_3_a, null) as NativeAdView
            }
            AdsLayout.THREE_B -> {
                inflater.inflate(R.layout.admob_native_3_b, null) as NativeAdView
            }
            AdsLayout.FOUR_A -> {
                inflater.inflate(R.layout.admob_native_4_a, null) as NativeAdView
            }
            AdsLayout.FOUR_B -> {
                inflater.inflate(R.layout.admob_native_4_b, null) as NativeAdView
            }
            AdsLayout.SEVEN_A -> {
                inflater.inflate(R.layout.admob_native_7_a, null) as NativeAdView
            }
            AdsLayout.SEVEN_B -> {
                inflater.inflate(R.layout.admob_native_7_b, null) as NativeAdView
            }
            AdsLayout.SEVEN_C -> {
                inflater.inflate(R.layout.admob_native_7_c, null) as NativeAdView
            }
        }

        adConfigurations.adMobContainer.visibility = View.VISIBLE
        adConfigurations.adMobContainer.removeAllViews()
        adConfigurations.adMobContainer.addView(adView)

        adView.headlineView = adView.findViewById(R.id.ad_headline)
        adView.bodyView = adView.findViewById(R.id.ad_body)
        adView.callToActionView = adView.findViewById(R.id.ad_call_to_action)
        adView.iconView = adView.findViewById(R.id.ad_app_icon)

        //Media
        adView.findViewById<MediaView>(R.id.ad_media)?.apply {
            adView.mediaView = this
        }

        //Media Background
        if (adView.findViewById<ImageView>(R.id.ad_media_bg) != null) {
            nativeAd.mediaContent?.mainImage?.let {
                Glide.with(context.applicationContext).load(it).transform(BlurTransformation(25, 3)).into(adView.findViewById(R.id.ad_media_bg))
            }
        }

        //Headline
        adView.findViewById<TextView>(R.id.ad_headline)?.apply {
            text = nativeAd.headline
            isSelected = true
        }

        //Body
        adView.findViewById<TextView>(R.id.ad_body)?.apply {
            if (nativeAd.body == null) {
                View.INVISIBLE
            } else {
                View.VISIBLE
                text = nativeAd.body
            }
        }

        //Call to Action
        adView.findViewById<TextView>(R.id.ad_call_to_action)?.apply {
            text = nativeAd.callToAction
        }

        //Icon
        if (adView.iconView != null) {
            if (nativeAd.icon == null) {
                adView.iconView!!.visibility = View.GONE
            } else {
                (adView.iconView as ImageView).setImageDrawable(nativeAd.icon!!.drawable)
                (adView.iconView as ImageView).visibility = View.VISIBLE
            }
        }

        //price
        if (adView.priceView != null) {
            if (nativeAd.price == null) {
                adView.priceView!!.visibility = View.GONE
            } else {
                adView.priceView!!.visibility = View.GONE
                (adView.priceView as TextView).text = nativeAd.price
            }
        }

        //Store
        if (adView.storeView != null) {
            if (nativeAd.store == null) {
                adView.storeView!!.visibility = View.GONE
            } else {
                adView.storeView!!.visibility = View.GONE
                (adView.storeView as TextView).text = nativeAd.store
            }
        }

        //Rating
        if (adView.starRatingView != null) {
            if (nativeAd.starRating != null) {
                (adView.starRatingView as RatingBar).rating = nativeAd.starRating!!.toFloat()
            }
            adView.starRatingView!!.visibility = View.GONE
        }

        //Advertiser
        if (adView.advertiserView != null) {
            if (nativeAd.advertiser != null) {
                (adView.advertiserView as TextView).text = nativeAd.advertiser
            }
            adView.advertiserView!!.visibility = View.GONE
        }

        setTitleColorAndFont(adView, adConfigurations.titleFontColor, adConfigurations.titleFontSize)
        setBodyColorAndFont(adView, adConfigurations.bodyFontColor, adConfigurations.bodyFontSize)
        setIconRound(adView, adConfigurations.iconCornerRadius, adConfigurations.iconBackGroundColor)
        setColorAndRoundAndFontCTA(adView, adConfigurations.CTARoundness, adConfigurations.CTABackgroundColor, adConfigurations.CTAFontSize, adConfigurations.CTAFontColor)
        setColorAndRoundAndFontAttribute(adView, adConfigurations.attrRadius, adConfigurations.attrBackgroundColor, adConfigurations.attrFontSize, adConfigurations.attrFontColor)

        adView.setNativeAd(nativeAd)
    }

    private fun setTitleColorAndFont(adView: NativeAdView, titleColor: String, titleFont: Float) {
        val title = adView.findViewById<TextView>(R.id.ad_headline)
        if (title != null) {
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleFont)
            if (titleColor.isNotEmpty()) {
                try {
                    title.setTextColor(Color.parseColor(titleColor))
                } catch (e: Exception) {
                    title.setTextColor(Color.parseColor("#000000"))
                } catch (e: java.lang.Exception) {
                    title.setTextColor(Color.parseColor("#000000"))
                }
            } else {
                title.setTextColor(Color.parseColor("#000000"))
            }
        }
    }

    private fun setIconRound(adView: NativeAdView, iconRadius: Float, iconBackground: String) {
        val icon = adView.findViewById<ImageFilterView>(R.id.ad_app_icon)
        if (icon != null) {
            icon.roundPercent = iconRadius
            if (iconBackground.isNotEmpty()) {
                try {
                    icon.setBackgroundColor(Color.parseColor(iconBackground))
                } catch (e: Exception) {
                    icon.setBackgroundColor(Color.parseColor("#F3F3F3"))
                } catch (e: java.lang.Exception) {
                    icon.setBackgroundColor(Color.parseColor("#F3F3F3"))
                }
            } else {
                icon.setBackgroundColor(Color.parseColor("#F3F3F3"))
            }
        }
    }

    private fun setBodyColorAndFont(adView: NativeAdView, bodyColor: String, bodyFont: Float) {
        val body = adView.findViewById<TextView>(R.id.ad_body)
        if (body != null) {
            body.setTextSize(TypedValue.COMPLEX_UNIT_SP, bodyFont)
            if (bodyColor.isNotEmpty()) {
                try {
                    body.setTextColor(Color.parseColor(bodyColor))
                } catch (e: Exception) {
                    body.setTextColor(Color.parseColor("#000000"))
                } catch (e: java.lang.Exception) {
                    body.setTextColor(Color.parseColor("#000000"))
                }
            } else {
                body.setTextColor(Color.parseColor("#000000"))
            }
        }
    }

    private fun setColorAndRoundAndFontCTA(adView: NativeAdView, ctaRound: Float, ctaColor: String, ctaFont: Float, ctaFontColor: String) {
        val ivCallToActionBackground = adView.findViewById<ImageFilterView>(R.id.call_to_action_bg)
        val tvCallToAction = adView.findViewById<TextView>(R.id.ad_call_to_action)
        if (ivCallToActionBackground != null && tvCallToAction != null) {
            ivCallToActionBackground.roundPercent = ctaRound
            if (ctaColor.isNotEmpty()) {
                try {
                    ivCallToActionBackground.setBackgroundColor(Color.parseColor(ctaColor))
                } catch (e: Exception) {
                    ivCallToActionBackground.setBackgroundColor(Color.parseColor("#E92222"))
                } catch (e: java.lang.Exception) {
                    ivCallToActionBackground.setBackgroundColor(Color.parseColor("#E92222"))
                }
            } else {
                ivCallToActionBackground.setBackgroundColor(Color.parseColor("#E92222"))
            }

            tvCallToAction.setTextSize(TypedValue.COMPLEX_UNIT_SP, ctaFont)

            if (ctaFontColor.isNotEmpty()) {
                try {
                    tvCallToAction.setTextColor(Color.parseColor(ctaFontColor))
                } catch (e: Exception) {
                    tvCallToAction.setTextColor(Color.parseColor("#FFFFFF"))
                } catch (e: java.lang.Exception) {
                    tvCallToAction.setTextColor(Color.parseColor("#FFFFFF"))
                }
            } else {
                tvCallToAction.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

    private fun setColorAndRoundAndFontAttribute(adView: NativeAdView, attRadius: Float, attBackgroundColor: String, attFont: Float, attFontColor: String) {
        val attr = adView.findViewById<TextView>(R.id.ad_attribute)
        if (attr != null) {

            val shape = GradientDrawable()
            shape.cornerRadius = attRadius
            if (attBackgroundColor.isNotEmpty()) {
                try {
                    shape.setColor(Color.parseColor(attBackgroundColor))
                } catch (e: Exception) {
                    shape.setColor(Color.parseColor("#FF9800"))
                } catch (e: java.lang.Exception) {
                    shape.setColor(Color.parseColor("#FF9800"))
                }
            } else {
                shape.setColor(Color.parseColor("#FF9800"))
            }
            attr.background = shape

            attr.setTextSize(TypedValue.COMPLEX_UNIT_SP, attFont)
            if (attFontColor.isNotEmpty()) {
                try {
                    attr.setTextColor(Color.parseColor(attFontColor))
                } catch (e: Exception) {
                    attr.setTextColor(Color.parseColor("#FFFFFF"))
                } catch (e: java.lang.Exception) {
                    attr.setTextColor(Color.parseColor("#FFFFFF"))
                }
            } else {
                attr.setTextColor(Color.parseColor("#FFFFFF"))
            }
        }
    }

}