# Funsol Native Ad Helper
Helper class to implement native ads easily.

## How To:
### Adding Library
1. Add this to All Projects. 
``` 
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
2. Add dependency
```
implementation 'com.github.Funsol-Projects:FunsolNativeAdHelper:Tag'
```

### Implementation Code - To use default layouts

#### In Layout file, Add
```
<com.funsol.nativeadhelper.NativeAdView
            android:id="@+id/nativeAdContainer1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
```

#### In Code, Add
```
NativeAdHelper(
            context, AdConfigurations(
                nativeContainer = binding.nativeAdContainer1.adPlaceHolder,
                adMobContainer = binding.nativeAdContainer1.adFrame,
                adLayout = AdsLayout.ONE_A,
                ...
		...
            )
        )
```

#### 


## TODO:
- [x] Creating Basic Native Ad Layout Confugration to modify AD looks.
- [ ]  Refresh Ad after some interval, if user stays on that screen for long time.
- [ ] Multi Ads: Request No. of Ads and keep show them on different screens. At the end of last AD, re-request Ads Again.
- [ ] AD Saving: If User load an AD on screen and quickly change the screen. If no impression recorded, save the AD and show on some other screen.
- [ ] AD Reference on Screens: Keep all ADs saved with thier Screen reference. Show Ad again, if user come back.
- [ ] Ad Adapter: Create a recycler view with get list from user and item place AD into it. and return list back to user. Or Any other good approch.
