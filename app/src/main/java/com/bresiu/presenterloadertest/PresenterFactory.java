package com.bresiu.presenterloadertest;

import android.os.Bundle;

import com.bresiu.presenterloadertest.worker.CheckTaskController;
import com.bresiu.presenterloadertest.worker.InitialController;

public class PresenterFactory {
    private int presenterOption;

    public PresenterFactory(Bundle args) {
        presenterOption = args.getInt(ChooseActivity.OPTION);
    }

    public Contract.Presenter create() {
        return new MainPresenter(new CheckTaskController(), new InitialController(), presenterOption);
    }
}
