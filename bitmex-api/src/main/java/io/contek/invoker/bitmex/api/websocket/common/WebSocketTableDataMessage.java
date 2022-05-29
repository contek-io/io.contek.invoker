package io.contek.invoker.bitmex.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import java.util.ArrayList;
import java.util.List;

public abstract class WebSocketTableDataMessage<T> extends AnyWebSocketMessage {

  public String table;
  public String action;
  public WebSocketFilter filter;
  public List<T> data = new ArrayList<>();
}
