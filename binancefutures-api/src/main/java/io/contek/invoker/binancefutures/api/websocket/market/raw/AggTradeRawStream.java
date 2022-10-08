package io.contek.invoker.binancefutures.api.websocket.market.raw;

import io.contek.invoker.binancefutures.api.websocket.market.AggTradeEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.common.constants.WebSocketChannelKeys.aggTrade;

@ThreadSafe
public final class AggTradeRawStream extends RawStream<AggTradeEvent> {

  AggTradeRawStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketRawChannelId<AggTradeEvent> {

    private Id(String symbol) {
      super(aggTrade(symbol));
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }

    @Override
    protected Class<AggTradeEvent> getType() {
      return AggTradeEvent.class;
    }
  }
}
