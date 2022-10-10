package io.contek.invoker.binancedelivery.api.websocket.market.combined;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventData;
import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
abstract class WebSocketStreamMessage<T extends WebSocketEventData> extends AnyWebSocketMessage {

  public String stream;
  public T data;
}
