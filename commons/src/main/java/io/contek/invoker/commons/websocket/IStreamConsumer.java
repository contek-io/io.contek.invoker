package io.contek.invoker.commons.websocket;

public interface IStreamConsumer<T> {

  void onNext(T t);

  ConsumerState getState();
}
