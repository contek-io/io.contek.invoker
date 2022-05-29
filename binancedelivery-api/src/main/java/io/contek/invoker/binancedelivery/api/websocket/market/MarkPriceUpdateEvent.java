package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;

public class MarkPriceUpdateEvent extends WebSocketEventMessage {

  public String s; // Symbol
  public Double p; // Mark price
  public Double r; // Funding rate
  public Long T; // Next funding time

  @Override
  public String toString() {
    return "MarkPriceUpdateEvent{" +
            "s='" + s + '\'' +
            ", p=" + p +
            ", r=" + r +
            ", T=" + T +
            '}';
  }
}
