package io.contek.invoker.bitstamp.api.websocket.common.constants;

public final class WebSocketEventKeys {

  public static final String _bts = "bts";
  public static final String _bts_subscribe = "bts:subscribe";
  public static final String _bts_unsubscribe = "bts:unsubscribe";
  public static final String _bts_subscription_succeeded = "bts:subscription_succeeded";
  public static final String _bts_unsubscription_succeeded = "bts:unsubscription_succeeded";

  public static final String _trade = "trade";
  public static final String _data = "data";

  private WebSocketEventKeys() {}
}
