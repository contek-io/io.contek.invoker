package io.contek.invoker.binancelinear.api.websocket.market.direct;

import io.contek.invoker.binancelinear.api.websocket.market.ForceOrderEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancelinear.api.websocket.common.constants.WebSocketChannelKeys.forceOrder;

@ThreadSafe
public final class ForceOrderDirectStream extends DirectStream<ForceOrderEvent> {

  public ForceOrderDirectStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketDirectChannelId<ForceOrderEvent> {

    private Id(String symbol) {
      super(forceOrder(symbol));
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }

    @Override
    protected Class<ForceOrderEvent> getType() {
      return ForceOrderEvent.class;
    }
  }
}
