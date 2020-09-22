package io.contek.invoker.bitstamp.api.websocket.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketEvents {

  public static final String bts = "bts";
  public static final String bts_subscribe = "bts:subscribe";
  public static final String bts_unsubscribe = "bts:unsubscribe";
  public static final String bts_subscription_succeeded = "bts:subscription_succeeded";
  public static final String bts_unsubscription_succeeded = "bts:unsubscription_succeeded";

  public static final String trade = "trade";
  public static final String data = "data";

  private WebSocketEvents() {}
}
