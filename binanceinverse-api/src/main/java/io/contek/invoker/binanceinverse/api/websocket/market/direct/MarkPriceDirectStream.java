package io.contek.invoker.binanceinverse.api.websocket.market.direct;

import io.contek.invoker.binanceinverse.api.websocket.market.MarkPriceUpdateEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binanceinverse.api.websocket.common.constants.WebSocketChannelKeys.markPrice;

@ThreadSafe
public final class MarkPriceDirectStream extends DirectStream<MarkPriceUpdateEvent> {

  public MarkPriceDirectStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketDirectChannelId<MarkPriceUpdateEvent> {

    private Id(String symbol, String interval) {
      super(markPrice(symbol, interval));
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }

    @Override
    protected Class<MarkPriceUpdateEvent> getType() {
      return MarkPriceUpdateEvent.class;
    }
  }
}
