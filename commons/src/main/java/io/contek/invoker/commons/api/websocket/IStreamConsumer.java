package io.contek.invoker.commons.api.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IStreamConsumer<T> {

  void onNext(T t);

  ConsumerState getState();
}
