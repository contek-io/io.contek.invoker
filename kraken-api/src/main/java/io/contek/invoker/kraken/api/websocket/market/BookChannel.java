package io.contek.invoker.kraken.api.websocket.market;

import io.contek.invoker.kraken.api.common._Book;
import io.contek.invoker.kraken.api.websocket.WebSocketChannel;
import io.contek.invoker.kraken.api.websocket.WebSocketChannelId;
import io.contek.invoker.kraken.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelDataMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys._book;

@ThreadSafe
public final class BookChannel extends WebSocketChannel<BookChannel.Id, BookChannel.Message> {

  BookChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  protected Subscription getSubscription() {
    Id id = getId();
    Subscription subscription = new Subscription();
    subscription.name = _book;
    subscription.depth = id.getDepth();
    return subscription;
  }

  @Override
  public Class<BookChannel.Message> getMessageType() {
    return BookChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<BookChannel.Message> {

    private final int depth;

    private Id(String pair, int depth) {
      super(_book, pair);
      this.depth = depth;
    }

    public static Id of(String pair, int depth) {
      return new Id(pair, depth);
    }

    public int getDepth() {
      return depth;
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelDataMessage<_Book> {}
}
