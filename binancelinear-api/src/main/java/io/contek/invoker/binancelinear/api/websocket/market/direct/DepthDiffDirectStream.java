package io.contek.invoker.binancelinear.api.websocket.market.direct;

import io.contek.invoker.binancelinear.api.websocket.common.constants.WebSocketChannelKeys;
import io.contek.invoker.binancelinear.api.websocket.market.DepthUpdateEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class DepthDiffDirectStream extends DirectStream<DepthUpdateEvent> {

  public DepthDiffDirectStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketDirectChannelId<DepthUpdateEvent> {

    private Id(String symbol, String interval) {
      super(WebSocketChannelKeys.depth(symbol, interval));
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }

    @Override
    protected Class<DepthUpdateEvent> getType() {
      return DepthUpdateEvent.class;
    }
  }
}
