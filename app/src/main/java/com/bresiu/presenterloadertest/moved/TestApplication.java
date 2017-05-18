package com.bresiu.presenterloadertest.moved;

import android.app.Application;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;

public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        Log.d("BRS", "LeakCanary.install");
        LeakCanary.install(this);
    }
}
