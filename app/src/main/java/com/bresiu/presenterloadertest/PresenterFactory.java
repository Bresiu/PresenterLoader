package com.bresiu.presenterloadertest;

public interface PresenterFactory<T extends Presenter> {
  T create();
}
