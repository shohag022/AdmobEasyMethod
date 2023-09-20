package com.rbmjltd.admobeasysdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button showInterstitial, showReword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AdUnit_ID_M.BANNER = getString(R.string.BANNER);
        AdUnit_ID_M.INTERSTITIAL = getString(R.string.INTERSTITIAL);
        AdUnit_ID_M.REWARDED = getString(R.string.REWARDED);

        // SDK Initialize
        RBMJ_Limited.loadInterstitialAds(MainActivity.this);
        RBMJ_Limited.loadRewordedAds(MainActivity.this);

        showInterstitial = findViewById(R.id.showInterstitial);
        showReword = findViewById(R.id.showReword);

        // Show Banner
        RBMJ_Limited.setBanner(findViewById(R.id.showBanner),MainActivity.this);

        showInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showInterstitialAds();

            }
        });

        showReword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showRewordAds();

            }

        });


    }//===========OnCreate Bundle End =========================================================

    private void showInterstitialAds() {
        // Show Interstitial
        new RBMJ_Limited(new onDismissMethode() {
            @Override
            public void onDismiss() {

            }
        }).ShowInterstitial(MainActivity.this,true);

    }

    private void showRewordAds() {
        // Show Reword
        new RBMJ_Limited(new onDismissMethode() {
            @Override
            public void onDismiss() {
                // when ads watch give some reworded
            }
        }).ShowRewarded(MainActivity.this,true);
    }


}