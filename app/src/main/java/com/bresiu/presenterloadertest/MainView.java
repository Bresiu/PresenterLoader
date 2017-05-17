package com.bresiu.presenterloadertest;

public interface MainView {
  void stopProgress();

  void startProgress();

  void setStateButtonText(String text);
}
