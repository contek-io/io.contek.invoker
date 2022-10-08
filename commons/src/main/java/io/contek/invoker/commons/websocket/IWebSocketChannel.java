package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface IWebSocketChannel<Data> {

  void addConsumer(ISubscribingConsumer<Data> consumer);
}
