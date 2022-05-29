package io.contek.invoker.commons.websocket;

public interface ISubscribingConsumer<T> extends IStreamConsumer<T> {

  void onStateChange(SubscriptionState state);
}
