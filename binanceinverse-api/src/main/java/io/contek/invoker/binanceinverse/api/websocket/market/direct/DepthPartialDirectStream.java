package io.contek.invoker.binanceinverse.api.websocket.market.direct;

import io.contek.invoker.binanceinverse.api.websocket.market.DepthUpdateEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binanceinverse.api.websocket.common.constants.WebSocketChannelKeys.depthPartial;

@ThreadSafe
public final class DepthPartialDirectStream extends DirectStream<DepthUpdateEvent> {

  public DepthPartialDirectStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketDirectChannelId<DepthUpdateEvent> {

    private Id(String symbol, int levels, @Nullable String interval) {
      super(depthPartial(symbol, levels, interval));
    }

    public static Id of(String symbol, int levels, @Nullable String interval) {
      return new Id(symbol, levels, interval);
    }

    @Override
    protected Class<DepthUpdateEvent> getType() {
      return DepthUpdateEvent.class;
    }
  }
}
