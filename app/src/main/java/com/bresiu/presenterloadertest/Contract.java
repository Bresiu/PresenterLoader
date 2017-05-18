package com.bresiu.presenterloadertest;

public interface Contract {

    interface Presenter {
        void start();

        void stop();

        void onViewAttached(MainView view);

        void onViewDetached();

        void onDestroyed();

        void onTriggerClick();

        void onAddClick();

        void onLookupClick();

        void onRemoveClick();
    }

    interface MainView {
        void stopProgress();

        void startProgress();

        void setStateButtonText(String text);

        void setTitle(int presenterOption);
    }
}
