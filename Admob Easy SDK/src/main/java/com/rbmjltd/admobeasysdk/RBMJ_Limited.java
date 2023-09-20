package com.rbmjltd.admobeasysdk;

import android.app.Activity;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class RBMJ_Limited {

    static onDismissMethode onDismissMethode;

    public RBMJ_Limited(onDismissMethode onDismissMethode) {
        this.onDismissMethode = onDismissMethode;
    }

    public RBMJ_Limited() {
    }

    public static void setBanner(LinearLayout banner, Context context) {

        if (AdUnit_ID_M.isAds) {
            MobileAds.initialize(context, initializationStatus -> {
            });
            AdView adView = new AdView(context);
            banner.addView(adView);
            adView.setAdUnitId(AdUnit_ID_M.BANNER);
            adView.setAdSize(AdSize.BANNER);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }

    }
    public static void loadInterstitialAds(Context context) {
        if (AdUnit_ID_M.isAds) {
            MobileAds.initialize(context, initializationStatus -> {
            });

            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(context, AdUnit_ID_M.INTERSTITIAL, adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                            AdUnit_ID_M.mInterstitialAd = interstitialAd;
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            AdUnit_ID_M.mInterstitialAd = null;
                        }
                    });
        }

    }
    public void ShowInterstitial(Activity activity, boolean isReload) {

        if (AdUnit_ID_M.mInterstitialAd != null) {
            AdUnit_ID_M.mInterstitialAd.show(activity);

            AdUnit_ID_M.mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();

                    if (isReload) {
                        AdUnit_ID_M.mInterstitialAd = null;
                        RBMJ_Limited.loadInterstitialAds(activity);
                    }

                    onDismissMethode.onDismiss();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    onDismissMethode.onDismiss();
                }
            });

        } else {
            onDismissMethode.onDismiss();
        }

    }

    public static void loadRewordedAds(Context context) {
        if (AdUnit_ID_M.isAds) {
            MobileAds.initialize(context, initializationStatus -> {
            });

            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedAd.load(context, AdUnit_ID_M.REWARDED,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error.

                            AdUnit_ID_M.mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            AdUnit_ID_M.mRewardedAd = rewardedAd;

                        }
                    });
        }

    }

    public void ShowRewarded(Activity activity, boolean isReload) {

        if (AdUnit_ID_M.mRewardedAd != null) {
            AdUnit_ID_M.mRewardedAd.show(activity, rewardItem -> {
                Toast.makeText(activity, "Reward Collected", Toast.LENGTH_SHORT).show();
            });

            AdUnit_ID_M.mRewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent();

                    if (isReload) {
                        AdUnit_ID_M.mRewardedAd = null;
                        RBMJ_Limited.loadRewordedAds(activity);
                    }

                    onDismissMethode.onDismiss();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                    //onDismiss.onDismiss();
                    Toast.makeText(activity, "Please try again later...", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            //onDismiss.onDismiss();
            Toast.makeText(activity, "Please Wait Ads is loading...", Toast.LENGTH_SHORT).show();
        }


    }


}
