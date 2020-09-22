package io.contek.invoker.bybit.api.websocket.common;

import io.contek.invoker.commons.api.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.ArrayList;
import java.util.List;

@NotThreadSafe
public final class WebSocketRequest extends AnyWebSocketMessage {

  public String op;
  public List<String> args = new ArrayList<>();
}
