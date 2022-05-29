package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import java.util.ArrayList;
import java.util.List;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._book;
import static java.lang.String.format;

public final class BookChangeChannel
    extends MarketWebSocketChannel<BookChangeChannel.Id, BookChangeChannel.Message> {

  BookChangeChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<BookChangeChannel.Message> getMessageType() {
    return BookChangeChannel.Message.class;
  }

  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, String interval) {
      return new Id(format("%s.%s.%s", _book, instrumentName, interval));
    }
  }

  public static final class Message extends WebSocketSingleChannelMessage<Data> {}

  public static final class Data {

    public String type;
    public long timestamp;
    public String instrument_name;
    public Long prev_change_id;
    public long change_id;
    public List<LevelUpdate> bids;
    public List<LevelUpdate> asks;
  }

  public static final class LevelUpdate extends ArrayList<String> {}
}
