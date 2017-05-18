package com.bresiu.presenterloadertest.moved;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.bresiu.presenterloadertest.Contract;
import com.bresiu.presenterloadertest.PresenterFactory;

public abstract class BaseFragment extends Fragment implements Contract.MainView {

    private static final String TAG = "BRS";
    private static final int LOADER_ID = 101;

    private Contract.Presenter presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "onActivityCreated");

        Loader<Contract.Presenter> loader = getLoaderManager().getLoader(loaderId());
        if (loader == null) {
            initLoader(getArguments());
        } else {
            this.presenter = ((PresenterLoader) loader).getPresenter();
            onPresenterCreatedOrRestored(presenter);
        }
    }

    private void initLoader(Bundle args) {
        if (args == null) {
            Log.d("BRS", "args null initLoader");
        }
        // LoaderCallbacks as an object, so no hint regarding loader will be leak to the subclasses.
        getLoaderManager().initLoader(loaderId(), args, new LoaderManager.LoaderCallbacks<Contract.Presenter>() {
            @Override
            public final Loader<Contract.Presenter> onCreateLoader(int id, Bundle args) {
                Log.i(TAG, "onCreateLoader");
                return new PresenterLoader(getContext(), getPresenterFactory(args));
            }

            @Override
            public final void onLoadFinished(Loader<Contract.Presenter> loader, Contract.Presenter presenter) {
                Log.i(TAG, "onLoadFinished");
                BaseFragment.this.presenter = presenter;
                onPresenterCreatedOrRestored(presenter);
            }

            @Override
            public final void onLoaderReset(Loader<Contract.Presenter> loader) {
                Log.i(TAG, "onLoaderReset");
                BaseFragment.this.presenter = null;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
        presenter.onViewAttached(getPresenterView());
    }

    @Override
    public void onStop() {
        presenter.onViewDetached();
        super.onStop();
        Log.i(TAG, "onStop");
    }

    /**
     * Instance of {@link PresenterFactory} use to create a Presenter when needed. This instance
     * should not contain {@link android.app.Activity} context reference since it will be keep on
     * rotations.
     */

    @NonNull
    protected PresenterFactory getPresenterFactory(Bundle args) {
        return new PresenterFactory(args);
    }

    /**
     * Hook for subclasses that deliver the {@link Contract.Presenter} before its View is attached.
     * Can be use to initialize the Presenter or simple hold a reference to it.
     */
    protected abstract void onPresenterCreatedOrRestored(@NonNull Contract.Presenter presenter);

    /**
     * Override in case of fragment not implementing Presenter<View> interface
     */
    @NonNull
    protected Contract.MainView getPresenterView() {
        return this;
    }

    /**
     * Use this method in case you want to specify a spefic ID for the {@link PresenterLoader}.
     * By default its value would be {@link #LOADER_ID}.
     */
    protected int loaderId() {
        return LOADER_ID;
    }
}
