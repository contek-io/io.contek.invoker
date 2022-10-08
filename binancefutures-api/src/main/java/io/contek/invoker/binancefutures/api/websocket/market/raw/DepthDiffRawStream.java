package io.contek.invoker.binancefutures.api.websocket.market.raw;

import io.contek.invoker.binancefutures.api.websocket.market.DepthUpdateEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.common.constants.WebSocketChannelKeys.depth;

@ThreadSafe
public final class DepthDiffRawStream extends RawStream<DepthUpdateEvent> {

  public DepthDiffRawStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketRawChannelId<DepthUpdateEvent> {

    private Id(String symbol, String interval) {
      super(depth(symbol, interval));
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
