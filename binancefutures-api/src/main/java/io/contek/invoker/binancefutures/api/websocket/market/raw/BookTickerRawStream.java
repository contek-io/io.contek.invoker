package io.contek.invoker.binancefutures.api.websocket.market.raw;

import io.contek.invoker.binancefutures.api.websocket.market.BookTickerEvent;
import io.contek.invoker.commons.actor.IActor;
import io.contek.invoker.commons.websocket.WebSocketContext;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.common.constants.WebSocketChannelKeys.bookTicker;

@ThreadSafe
public final class BookTickerRawStream extends RawStream<BookTickerEvent> {

  public BookTickerRawStream(Id id, IActor actor, WebSocketContext context) {
    super(id, actor, context);
  }

  @Immutable
  public static final class Id extends MarketWebSocketRawChannelId<BookTickerEvent> {

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
