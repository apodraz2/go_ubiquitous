package com.podraza.android.habit.sunshinewear;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends WearableActivity {
    private final String LOG_TAG = getClass().getSimpleName();

    private BroadcastReceiver mMessageReceiver;
    private double mHigh;
    private double mLow;
    private int mWeatherId;

    private TextView mHighView;
    private TextView mLowView;
    private ImageView mImageView;

    private static final SimpleDateFormat AMBIENT_DATE_FORMAT =
            new SimpleDateFormat("HH:mm", Locale.US);

    private BoxInsetLayout mContainerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAmbientEnabled();

        mContainerView = (BoxInsetLayout) findViewById(R.id.container);

        mHighView = (TextView) mContainerView.findViewById(R.id.high_view);
        mLowView = (TextView) mContainerView.findViewById(R.id.low_view);
        mImageView = (ImageView) mContainerView.findViewById(R.id.weather_view);

        if((Integer) mWeatherId == null) {
            mHighView.setText(Double.toString(20));
            mLowView.setText(Double.toString(10));

            mImageView.setImageResource(Utility.getIconResourceForWeatherCondition(1));
        }


        mMessageReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d(LOG_TAG, "onReceive");
                mHigh = Math.ceil(intent.getDoubleExtra("temp-high", 20));
                mLow = Math.ceil(intent.getDoubleExtra("temp-low", 0));
                mWeatherId = intent.getIntExtra("weather-id", 0);


                mHighView.setText(Double.toString(mHigh));
                mLowView.setText(Double.toString(mLow));

                mImageView.setImageResource(Utility.getIconResourceForWeatherCondition(mWeatherId));

            }
        };


        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter("weather-data"));

    }

    @Override
    public void onEnterAmbient(Bundle ambientDetails) {
        super.onEnterAmbient(ambientDetails);
        updateDisplay();
    }

    @Override
    public void onUpdateAmbient() {
        super.onUpdateAmbient();
        updateDisplay();
    }

    @Override
    public void onExitAmbient() {
        updateDisplay();
        super.onExitAmbient();
    }

    private void updateDisplay() {
        if (isAmbient()) {
            mContainerView.setBackgroundColor(getResources().getColor(android.R.color.black));
            mHighView.setText((int) mHigh);
            mLowView.setText((int) mLow);

        } else {
            Log.d(LOG_TAG, "updateDisplay");
            mContainerView.setBackground(null);
            mHighView.setText((int) mHigh);
            mLowView.setText((int) mLow);
        }
    }


}
