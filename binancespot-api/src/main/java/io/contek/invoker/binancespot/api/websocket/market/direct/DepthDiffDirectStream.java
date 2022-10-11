package io.contek.invoker.binancespot.api.websocket.market.direct;

import io.contek.invoker.binancespot.api.websocket.market.DepthDiffEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancespot.api.websocket.common.constants.WebSocketChannelKeys.depth;

@ThreadSafe
public final class DepthDiffDirectStream extends DirectStream<DepthDiffEvent> {

  public DepthDiffDirectStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketDirectChannelId<DepthDiffEvent> {

    private Id(String symbol, String interval) {
      super(depth(symbol, interval));
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }

    @Override
    protected Class<DepthDiffEvent> getType() {
      return DepthDiffEvent.class;
    }
  }
}
