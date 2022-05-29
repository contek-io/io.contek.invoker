package io.contek.invoker.bybitlinear.api.websocket.common;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import java.util.ArrayList;
import java.util.List;

public final class WebSocketOperationRequest extends AnyWebSocketMessage {

  public String op;
  public List<String> args = new ArrayList<>();
}
