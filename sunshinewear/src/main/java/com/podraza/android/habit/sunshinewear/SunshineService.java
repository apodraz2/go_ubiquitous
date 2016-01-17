package com.podraza.android.habit.sunshinewear;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

public class SunshineService extends WearableListenerService {
    private final String LOG_TAG = getClass().getSimpleName();

    private double high;
    private double low;
    private int weatherId;

    public SunshineService() {

    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for(DataEvent event : dataEvents) {
            if(event.getType() == DataEvent.TYPE_CHANGED) {

                DataMap map = DataMapItem.fromDataItem(event.getDataItem()).getDataMap();

                String path = event.getDataItem().getUri().getPath();

                if("/sunshine".equals(path)) {
                    high = map.getDouble("temp-high");
                    low = map.getDouble("temp-low");
                    weatherId = map.getInt("weather-id");



                }

            }
        }
    }
}
