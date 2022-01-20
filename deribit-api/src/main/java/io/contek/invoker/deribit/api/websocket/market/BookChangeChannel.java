package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._book;
import static java.lang.String.format;

@ThreadSafe
public final class BookChangeChannel
    extends MarketWebSocketChannel<BookChangeChannel.Id, BookChangeChannel.Message> {

  BookChangeChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<BookChangeChannel.Message> getMessageType() {
    return BookChangeChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, String interval) {
      return new Id(format("%s.%s.%s", _book, instrumentName, interval));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketSingleChannelMessage<Data> {}

  @NotThreadSafe
  public static final class Data {

    public String type;
    public long timestamp;
    public String instrument_name;
    public long prev_change_id;
    public long change_id;
    public List<LevelUpdate> bids;
    public List<LevelUpdate> asks;
  }

  @NotThreadSafe
  public static final class LevelUpdate extends ArrayList<String> {}
}
