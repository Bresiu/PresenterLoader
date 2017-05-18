package com.bresiu.presenterloadertest;

import android.util.Log;

import com.bresiu.presenterloadertest.worker.CheckTaskController;
import com.bresiu.presenterloadertest.worker.InitialController;
import com.bresiu.presenterloadertest.worker.TaskCallback;
import com.bresiu.presenterloadertest.worker.TaskController;
import com.bresiu.presenterloadertest.worker.TaskManager;

class MainPresenter implements Contract.Presenter {
    private final TaskController<Void, Void> initialController;

    private final TaskController<String, String> checkPermissionTask;
    private Contract.MainView mainView;
    private ViewState viewState;
    private int presenterOption;

    MainPresenter(final CheckTaskController checkPermissionTask,
                  InitialController initialController, int presenterOption) {
        this.initialController = initialController;
        this.checkPermissionTask = checkPermissionTask;
        this.presenterOption = presenterOption;
        viewState = ViewState.LOOKUP_STATE;
        startInitialTask();
    }

    private void startInitialTask() {
        initialController.startTask(new TaskCallback<Void>() {
            @Override
            public void onResult(Void result) {
                Log.d("BRS", "initial task result");
                if (mainView != null && !TaskManager.INSTANCE.isAnyTaskRunning()) {
                    mainView.stopProgress();
                }
            }
        });
    }

    public void start() {
        Log.d("BRS", "on start");
        if (TaskManager.INSTANCE.isAnyTaskRunning()) {
            mainView.startProgress();
        }
        setViewState();
    }

    private void setViewState() {
        Log.d("BRS", "setTitle: " + presenterOption);
        mainView.setTitle(presenterOption);
        mainView.setStateButtonText(String.valueOf(viewState));
    }

    public void stop() {
        Log.d("BRS", "on stop");
    }

    @Override
    public void onViewAttached(Contract.MainView view) {
        Log.d("BRS", "onViewAttached");
        mainView = view;
    }

    @Override
    public void onViewDetached() {
        Log.d("BRS", "onViewDetached");
    }

    @Override
    public void onDestroyed() {
        Log.d("BRS", "onDestroyed");
    }

    @Override
    public void onTriggerClick() {
        mainView.startProgress();
        checkPermissionTask.startTask(new TaskCallback<String>() {
            @Override
            public void onResult(String test) {
                Log.d("BRS", "onResult: " + test);
                if (mainView != null && !TaskManager.INSTANCE.isAnyTaskRunning()) {
                    mainView.stopProgress();
                }
            }
        }, String.valueOf(viewState));
    }

    @Override
    public void onAddClick() {
        viewState = ViewState.ADD_STATE;
        mainView.setStateButtonText(String.valueOf(viewState));
    }

    @Override
    public void onLookupClick() {
        viewState = ViewState.LOOKUP_STATE;
        mainView.setStateButtonText(String.valueOf(viewState));
    }

    @Override
    public void onRemoveClick() {
        viewState = ViewState.REMOVE_STATE;
        mainView.setStateButtonText(String.valueOf(viewState));
    }

    private enum ViewState {
        LOOKUP_STATE,
        ADD_STATE,
        REMOVE_STATE
    }
}