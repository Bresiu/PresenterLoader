package com.bresiu.presenterloadertest.worker;

import android.util.Log;

public class InitialController extends TaskController<Void, Void> {

    @Override
    Task<Void, Void> createNewTask(TaskCallback<Void> callback) {
        return new StartingAsyncTask(callback);
    }

    private static class StartingAsyncTask extends Task<Void, Void> {

        StartingAsyncTask(TaskCallback<Void> callback) {
            super(callback);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("BRS", "StartingAsyncTask Start work");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Log.d("BRS", "StartingAsyncTask InterruptedException: " + e);
            }
            return null;
        }
    }
}
