package com.bresiu.presenterloadertest.worker;

import android.util.Log;

public class CheckTaskController extends TaskController<String, String> {

    @Override
    Task<String, String> createNewTask(TaskCallback<String> callback) {
        return new CheckPermissionAsyncTask(callback);
    }

    private static class CheckPermissionAsyncTask extends Task<String, String> {

        CheckPermissionAsyncTask(TaskCallback<String> callback) {
            super(callback);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d("BRS", "CheckPermissionAsyncTask Start work");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Log.d("BRS", "CheckPermissionAsyncTask InterruptedException: " + e);
            }
            return "CheckPermissionAsyncTask: " + strings[0];
        }
    }
}
