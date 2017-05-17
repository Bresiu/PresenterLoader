package com.bresiu.presenterloadertest;

import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.util.Log;

public abstract class TaskController<Params, Result>
    implements TaskControllerContract<Params, Result> {
  private AsyncTask<Params, Void, Result> mAsyncTask;

  @SuppressWarnings("unchecked")
  @Override public void startTask(TaskCallback<Result> callback) {
    if (mAsyncTask != null) {
      cancelTask();
    }
    Log.d("BRS", "startTask");
    mAsyncTask = createNewTask(callback).execute((Params[]) new Void[0]);
  }

  @SuppressWarnings("unchecked") @MainThread @Override
  public final void startTask(TaskCallback<Result> callback, Params... params) {
    if (mAsyncTask != null) {
      cancelTask();
    }
    Log.d("BRS", "startTask");
    mAsyncTask = createNewTask(callback).execute(params);
  }

  @MainThread @Override public void cancelTask() {
    if (!mAsyncTask.isCancelled()) {
      Log.d("BRS", "cancelTask");
      mAsyncTask.cancel(false);
    }
  }

  abstract Task<Params, Result> createNewTask(TaskCallback<Result> callback);

  abstract static class Task<Params, Result> extends AsyncTask<Params, Void, Result> {
    TaskCallback<Result> callback;

    Task(TaskCallback<Result> callback) {
      this.callback = callback;
      TaskManager.INSTANCE.addRunningTask();
    }

    @Override protected void onCancelled() {
      callback = null;
      super.onCancelled();
      TaskManager.INSTANCE.removeRunningTask();
    }

    @SuppressWarnings("unchecked") @Override
    protected abstract Result doInBackground(Params... params);

    @Override protected void onPostExecute(Result result) {
      super.onPostExecute(result);
      TaskManager.INSTANCE.removeRunningTask();
      if (callback != null) {
        callback.onResult(result);
      }
    }
  }
}
