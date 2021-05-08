package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketChannel;
import io.contek.invoker.deribit.api.websocket.common.Params;
import io.contek.invoker.deribit.api.websocket.common.Trade;
import io.contek.invoker.deribit.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;
import java.util.Objects;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._trades;
import static java.lang.String.format;

@ThreadSafe
public final class TradesChannel extends WebSocketChannel<TradesChannel.Message> {

  private final Topic topic;

  TradesChannel(Topic topic) {
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
  protected Class<TradesChannel.Message> getMessageType() {
    return TradesChannel.Message.class;
  }

  @Override
  protected boolean accepts(TradesChannel.Message message) {
    return topic.getValue().equals(message.params.channel);
  }

  @Immutable
  public static final class Topic {

    private final String value;

    private Topic(String value) {
      this.value = value;
    }

    public static Topic of(String instrumentName, String interval) {
      return new Topic(format("%s.%s.%s", _trades, instrumentName, interval));
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
  public static final class Message extends WebSocketChannelMessage<Params<List<Trade>>> {}
}
