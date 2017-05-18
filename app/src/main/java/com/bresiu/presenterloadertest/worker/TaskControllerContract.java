package com.bresiu.presenterloadertest.worker;

interface TaskControllerContract<Params, Result> {

    void startTask(TaskCallback<Result> callback);

    @SuppressWarnings("unchecked")
    void startTask(TaskCallback<Result> callback, Params... params);

    void cancelTask();
}
