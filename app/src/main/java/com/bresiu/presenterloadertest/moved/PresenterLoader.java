package com.bresiu.presenterloadertest.moved;

import android.content.Context;
import android.support.v4.content.Loader;
import android.util.Log;

import com.bresiu.presenterloadertest.Contract;
import com.bresiu.presenterloadertest.PresenterFactory;

final class PresenterLoader extends Loader<Contract.Presenter> {

    private final PresenterFactory factory;
    private Contract.Presenter presenter;

    PresenterLoader(Context context, PresenterFactory factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        Log.i("BRS", "onStartLoading");

        // if we already own a presenter instance, simply deliver it.
        if (presenter != null) {
            deliverResult(presenter);
            return;
        }

        // Otherwise, force a load
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        Log.i("BRS", "onForceLoad");

        // Create the Presenter using the Factory
        presenter = factory.create();

        // Deliver the result
        deliverResult(presenter);
    }

    @Override
    public void deliverResult(Contract.Presenter data) {
        super.deliverResult(data);
        Log.i("BRS", "deliverResult");
    }

    @Override
    protected void onStopLoading() {
        Log.d("BRS", "onStopLoading");
    }

    @Override
    protected void onReset() {
        Log.d("BRS", "onReset");
        if (presenter != null) {
            presenter.onDestroyed();
            presenter = null;
        }
    }

    Contract.Presenter getPresenter() {
        return presenter;
    }
}