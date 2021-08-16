package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.websocket.WebSocketChannel;
import io.contek.invoker.bitstamp.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class LiveTradesChannel
    extends WebSocketChannel<LiveTradesChannel.Id, LiveTradesChannel.Message> {

  public static final String PREFIX = "live_trades_";

  LiveTradesChannel(LiveTradesChannel.Id id) {
    super(id);
  }

  @Override
  public Class<LiveTradesChannel.Message> getMessageType() {
    return LiveTradesChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<LiveTradesChannel.Message> {

    private Id(String currencyPair) {
      super(PREFIX + currencyPair);
    }

    public static Id of(String currencyPair) {
      return new Id(currencyPair);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelMessage<Data> {}

  @NotThreadSafe
  public static final class Data {

    public Long id;
    public Double amount;
    public String amount_str;
    public Double price;
    public String price_str;
    public Integer type;
    public Long timestamp;
    public Long microtimestamp;
    public Long buy_order_id;
    public Long sell_order_id;

    @Override
    public String toString() {
      return "Data{" +
              "id=" + id +
              ", amount=" + amount +
              ", amount_str='" + amount_str + '\'' +
              ", price=" + price +
              ", price_str='" + price_str + '\'' +
              ", type=" + type +
              ", timestamp=" + timestamp +
              ", microtimestamp=" + microtimestamp +
              ", buy_order_id=" + buy_order_id +
              ", sell_order_id=" + sell_order_id +
              '}';
    }
  }
}
