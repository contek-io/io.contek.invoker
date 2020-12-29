package io.contek.invoker.commons.api.websocket;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface ISubscribingConsumer<T> extends IStreamConsumer<T> {

  void onStateChange(SubscriptionState state);
}
