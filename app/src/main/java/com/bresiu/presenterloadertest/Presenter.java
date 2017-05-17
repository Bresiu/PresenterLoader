package com.bresiu.presenterloadertest;

public interface Presenter<V> {
  void onViewAttached(V view);

  void onViewDetached();

  void onDestroyed();

  void onTriggerClick();

  void onAddClick();

  void onLookupClick();

  void onRemoveClick();
}
