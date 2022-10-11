package io.contek.invoker.binancespot.api.websocket.market.direct;

import io.contek.invoker.binancespot.api.websocket.market.BookTickerEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancespot.api.websocket.common.constants.WebSocketChannelKeys.bookTicker;

@ThreadSafe
public final class BookTickerDirectStream extends DirectStream<BookTickerEvent> {

  public BookTickerDirectStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketDirectChannelId<BookTickerEvent> {

    private Id(String symbol) {
      super(bookTicker(symbol));
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }

    @Override
    protected Class<BookTickerEvent> getType() {
      return BookTickerEvent.class;
    }
  }
}
