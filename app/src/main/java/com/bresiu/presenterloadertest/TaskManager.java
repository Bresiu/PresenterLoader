package com.bresiu.presenterloadertest;

import android.util.Log;

public enum TaskManager {
  INSTANCE;

  private int runningTasks;

  TaskManager() {
    runningTasks = 0;
  }

  public void addRunningTask() {
    runningTasks++;
    Log.d("BRS", "addRunningTask, jobs size: " + runningTasks);
  }

  public void removeRunningTask() {
    runningTasks--;
    Log.d("BRS", "removeRunningTask, jobs size: " + runningTasks);
  }

  public boolean isAnyTaskRunning() {
    return runningTasks != 0;
  }
}
