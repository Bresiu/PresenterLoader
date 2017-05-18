package com.bresiu.presenterloadertest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.bresiu.presenterloadertest.moved.BaseFragment;
import com.bresiu.presenterloadertest.moved.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment implements Contract.MainView {
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.button)
    Button button;

    private Contract.Presenter mainPresenter;

    public static MainFragment getMainFragment(Bundle args) {
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        mainPresenter.stop();
    }

    public void startProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setStateButtonText(String text) {
        button.setText(text);
    }

    @Override
    public void setTitle(int presenterOption) {
        ((MainActivity) getActivity()).setActionBarTitle("Option : " + presenterOption);
    }

    public void stopProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.button)
    public void onButtonClick() {
        mainPresenter.onTriggerClick();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.add_button)
    public void onAddButtonClick() {
        mainPresenter.onAddClick();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.lookup_button)
    public void onLookupButtonClick() {
        mainPresenter.onLookupClick();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.remove_button)
    public void onRemoveButtonClick() {
        mainPresenter.onRemoveClick();
    }

    @Override
    protected void onPresenterCreatedOrRestored(@NonNull Contract.Presenter presenter) {
        Log.d("BRS", "onPresenterCreatedOrRestored");
        mainPresenter = presenter;
    }
}
