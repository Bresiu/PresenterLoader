package com.bresiu.presenterloadertest.worker;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class MainLoader extends AsyncTaskLoader<String> {
    private String mData;

    MainLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }

        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();

        if (mData != null) {
            mData = null;
        }
    }

    @Override
    public void onCanceled(String data) {
        super.onCanceled(data);
    }

    @Override
    public void deliverResult(String data) {
        mData = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    public String loadInBackground() {
        Log.d("BRS", "Start work");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Log.d("BRS", "InterruptedException: " + e);
        }
        Log.d("BRS", "Stop work");
        return "TEST";
    }
}
