package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ISubscribingConsumer<T> extends IStreamConsumer<T> {

  void onStateChange(SubscriptionState state);
}
