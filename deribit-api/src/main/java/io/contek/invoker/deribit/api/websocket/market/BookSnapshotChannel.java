package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.common._OrderBookLevel;
import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import java.util.List;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._book;
import static java.lang.String.format;

public final class BookSnapshotChannel
    extends MarketWebSocketChannel<BookSnapshotChannel.Id, BookSnapshotChannel.Message> {

  BookSnapshotChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<BookSnapshotChannel.Message> getMessageType() {
    return BookSnapshotChannel.Message.class;
  }

  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, String group, int depth, String interval) {
      return new Id(format("%s.%s.%s.%d.%s", _book, instrumentName, group, depth, interval));
    }
  }

  public static final class Message extends WebSocketSingleChannelMessage<Data> {}

  public static final class Data {

    public long timestamp;
    public String instrument_name;
    public long change_id;
    public List<_OrderBookLevel> bids;
    public List<_OrderBookLevel> asks;

    @Override
    public String toString() {
      return "Data{" +
              "timestamp=" + timestamp +
              ", instrument_name='" + instrument_name + '\'' +
              ", change_id=" + change_id +
              ", bids=" + bids +
              ", asks=" + asks +
              '}';
    }
  }
}
