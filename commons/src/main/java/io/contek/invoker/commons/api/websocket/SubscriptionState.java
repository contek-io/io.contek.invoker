package io.contek.invoker.commons.api.websocket;

import javax.annotation.concurrent.Immutable;

@Immutable
public enum SubscriptionState {
  UNSUBSCRIBED,
  SUBSCRIBING,
  SUBSCRIBED,
  UNSUBSCRIBING,
}
