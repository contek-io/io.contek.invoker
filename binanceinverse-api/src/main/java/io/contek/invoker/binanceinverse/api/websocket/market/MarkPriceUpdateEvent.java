package io.contek.invoker.binanceinverse.api.websocket.market;

import io.contek.invoker.binanceinverse.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class MarkPriceUpdateEvent extends WebSocketEventData {

  public String s; // Symbol
  public String p; // Mark price
  public String
      P; // Estimated Settle Price, only useful in the last hour before the settlement starts.
  public String r; // Funding rate
  public Long T; // Next funding time
}
