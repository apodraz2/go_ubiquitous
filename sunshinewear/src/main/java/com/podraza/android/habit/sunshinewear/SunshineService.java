package com.podraza.android.habit.sunshinewear;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

public class SunshineService extends WearableListenerService {
    public SunshineService() {
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for(DataEvent event : dataEvents) {
            if(event.getType() == DataEvent.TYPE_CHANGED) {

                DataMap map = DataMapItem.fromDataItem(event.getDataItem()).getDataMap();

                String path = event.getDataItem().getUri().getPath();

                if(path == "/sunshine") {

                }

            }
        }
    }
}
