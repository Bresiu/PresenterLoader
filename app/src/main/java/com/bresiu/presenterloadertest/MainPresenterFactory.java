package com.bresiu.presenterloadertest;

public class MainPresenterFactory implements PresenterFactory<MainPresenter> {
  @Override public MainPresenter create() {
    return new MainPresenter(new CheckTaskController(), new InitialController());
  }
}
