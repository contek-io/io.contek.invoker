package io.contek.invoker.deribit.api.websocket.market;

import io.contek.invoker.deribit.api.websocket.WebSocketChannelId;
import io.contek.invoker.deribit.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.deribit.api.websocket.common.WebSocketSingleChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static io.contek.invoker.deribit.api.websocket.common.constants.WebSocketChannelKeys._trades;
import static java.lang.String.format;

@ThreadSafe
public final class TradesChannel
    extends MarketWebSocketChannel<TradesChannel.Message, List<TradesChannel.Data>> {

  TradesChannel(TradesChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String value) {
      super(value);
    }

    public static Id of(String instrumentName, String interval) {
      return new Id(format("%s.%s.%s", _trades, instrumentName, interval));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketSingleChannelMessage<List<Data>> {}

  @NotThreadSafe
  public static final class Data {

    public long amount;
    public String block_trade_id;
    public String direction;
    public double index_price;
    public String instrument_name;
    public double iv;
    public String liquidation;
    public double mark_price;
    public double price;
    public int tick_direction;
    public long timestamp;
    public String trade_id;
    public long trade_seq;
  }
}
