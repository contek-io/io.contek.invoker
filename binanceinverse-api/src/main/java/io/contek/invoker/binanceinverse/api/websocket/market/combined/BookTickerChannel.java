package io.contek.invoker.binanceinverse.api.websocket.market.combined;

import io.contek.invoker.binanceinverse.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binanceinverse.api.websocket.market.BookTickerEvent;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binanceinverse.api.websocket.common.constants.WebSocketChannelKeys.bookTicker;

@ThreadSafe
public final class BookTickerChannel
    extends MarketCombinedChannel<BookTickerChannel.Message, BookTickerEvent> {

  BookTickerChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends MarketCombinedChannelId<Message> {

    private Id(String symbol) {
      super(bookTicker(symbol));
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<BookTickerEvent> {}
}
