package io.contek.invoker.binancespot.api.websocket.market.combined;

import io.contek.invoker.commons.websocket.AnyWebSocketMessage;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class WebSocketCommand extends AnyWebSocketMessage {

  public String method;
  public List<String> params;
  public Integer id;
}
