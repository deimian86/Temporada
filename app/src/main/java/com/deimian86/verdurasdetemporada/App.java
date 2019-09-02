package com.deimian86.verdurasdetemporada;

import android.app.Application;
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCenter.start(this, BuildConfig.MS_APPCENTER_SECRET, Analytics.class, Crashes.class);
    }

}