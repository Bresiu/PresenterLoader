package com.bresiu.presenterloadertest;

import android.util.Log;

class MainPresenter implements Presenter<MainView> {
  private final TaskController<Void, Void> initialController;

  private final TaskController<String, String> checkPermissionTask;
  private MainView mainView;
  private ViewState viewState;

  private enum ViewState {
    LOOKUP_STATE,
    ADD_STATE,
    REMOVE_STATE
  }

  MainPresenter(final CheckTaskController checkPermissionTask,
      InitialController initialController) {
    this.initialController = initialController;
    this.checkPermissionTask = checkPermissionTask;
    viewState = ViewState.LOOKUP_STATE;
    startInitialTask();
  }

  private void startInitialTask() {
    initialController.startTask(new TaskCallback<Void>() {
      @Override public void onResult(Void result) {
        Log.d("BRS", "initial task result");
        if (mainView != null && !TaskManager.INSTANCE.isAnyTaskRunning()) {
          mainView.stopProgress();
        }
      }
    });
  }

  void start() {
    Log.d("BRS", "on start");
    if (TaskManager.INSTANCE.isAnyTaskRunning()) {
      mainView.startProgress();
    }
    setViewState();
  }

  private void setViewState() {
    mainView.setStateButtonText(String.valueOf(viewState));
  }

  void stop() {
    Log.d("BRS", "on stop");
  }

  @Override public void onViewAttached(MainView view) {
    Log.d("BRS", "onViewAttached");
    mainView = view;
  }

  @Override public void onViewDetached() {
    Log.d("BRS", "onViewDetached");
  }

  @Override public void onDestroyed() {
    Log.d("BRS", "onDestroyed");
  }

  @Override public void onTriggerClick() {
    mainView.startProgress();
    checkPermissionTask.startTask(new TaskCallback<String>() {
      @Override public void onResult(String test) {
        Log.d("BRS", "onResult: " + test);
        if (mainView != null && !TaskManager.INSTANCE.isAnyTaskRunning()) {
          mainView.stopProgress();
        }
      }
    }, String.valueOf(viewState));
  }

  @Override public void onAddClick() {
    viewState = ViewState.ADD_STATE;
    mainView.setStateButtonText(String.valueOf(viewState));
  }

  @Override public void onLookupClick() {
    viewState = ViewState.LOOKUP_STATE;
    mainView.setStateButtonText(String.valueOf(viewState));
  }

  @Override public void onRemoveClick() {
    viewState = ViewState.REMOVE_STATE;
    mainView.setStateButtonText(String.valueOf(viewState));
  }
}