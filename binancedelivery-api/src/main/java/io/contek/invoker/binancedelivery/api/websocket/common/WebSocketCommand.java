package io.contek.invoker.binancedelivery.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import java.util.List;

public final class WebSocketCommand extends AnyWebSocketMessage {

  public String method;
  public List<String> params;
  public Integer id;
}
