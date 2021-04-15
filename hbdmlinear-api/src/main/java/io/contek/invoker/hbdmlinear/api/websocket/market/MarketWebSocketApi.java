package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;
import io.contek.invoker.hbdmlinear.api.websocket.WebSocketApi;

import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class MarketWebSocketApi extends WebSocketApi {

  public MarketWebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, context);
  }
}
