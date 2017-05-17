package com.bresiu.presenterloadertest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends BaseFragment<MainPresenter, MainView> implements MainView {
  @BindView(R.id.progress) ProgressBar progressBar;
  @BindView(R.id.button) Button button;

  private MainPresenter mainPresenter;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onResume() {
    super.onResume();
    mainPresenter.start();
  }

  @Override public void onPause() {
    super.onPause();
    mainPresenter.stop();
  }

  public void startProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void setStateButtonText(String text) {
    button.setText(text);
  }

  public void stopProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @NonNull @Override protected String tag() {
    return null;
  }

  @NonNull @Override protected PresenterFactory<MainPresenter> getPresenterFactory() {
    return new MainPresenterFactory();
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

  @Override protected void onPresenterCreatedOrRestored(@NonNull MainPresenter presenter) {
    Log.d("BRS", "onPresenterCreatedOrRestored");
    mainPresenter = presenter;
  }
}
