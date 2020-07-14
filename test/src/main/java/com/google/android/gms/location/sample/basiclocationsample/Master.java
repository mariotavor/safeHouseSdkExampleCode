package com.google.android.gms.location.sample.basiclocationsample;

import com.wireguard.android.Application;
import com.wireguard.android.SafeHouseSDK;

public class Master extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SafeHouseSDK.init(getApplicationContext(), BuildConfig.APPLICATION_ID, BuildConfig.VERSION_CODE, BuildConfig.VERSION_NAME);
    }
}
