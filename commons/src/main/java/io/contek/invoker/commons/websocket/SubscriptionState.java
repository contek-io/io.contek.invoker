package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum SubscriptionState {
  UNSUBSCRIBED,
  SUBSCRIBING,
  SUBSCRIBED,
  UNSUBSCRIBING,
}
