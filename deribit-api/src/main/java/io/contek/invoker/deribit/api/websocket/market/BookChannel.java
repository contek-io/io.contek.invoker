package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.OrderBook;
import io.contek.invoker.deribit.api.websocket.common.Params;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Objects;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._book;
import static java.lang.String.format;

@ThreadSafe
public final class BookChannel extends WebSocketChannel<BookChannel.Message> {

  private final Topic topic;

  BookChannel(Topic topic) {
    // "book.BTC-24SEP21.none.1.100ms"
    this.topic = topic;
  }

  @Override
  protected String getChannel() {
    return topic.getValue();
  }

  @Override
  protected BaseWebSocketChannelId getId() {
    return topic.getValue();
  }

  @Override
  protected Class<BookChannel.Message> getMessageType() {
    return BookChannel.Message.class;
  }

  @Override
  protected boolean accepts(BookChannel.Message message) {
    return topic.getValue().equals(message.params.channel);
  }

  @Immutable
  public static final class Topic {

    private final String value;

    private Topic(String value) {
      this.value = value;
    }

    public static Topic of(String instrumentName, String group, int depth, String interval) {
      return new Topic(format("%s.%s.%s.%d.%s", _book, instrumentName, group, depth, interval));
    }

    public String getValue() {
      return value;
    }

    @Override
    public boolean equals(@Nullable Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Topic topic = (Topic) o;
      return Objects.equals(value, topic.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Params<OrderBook>> {}
}
