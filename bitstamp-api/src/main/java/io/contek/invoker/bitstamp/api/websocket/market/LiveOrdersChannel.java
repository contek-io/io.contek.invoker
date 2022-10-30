package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.websocket.WebSocketChannel;
import io.contek.invoker.bitstamp.api.websocket.WebSocketChannelId;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class LiveOrdersChannel
    extends WebSocketChannel<LiveOrdersChannel.Id, LiveOrdersChannel.Message> {

  public static final String PREFIX = "live_orders_";

  LiveOrdersChannel(LiveOrdersChannel.Id id) {
    super(id);
  }

  @Override
  public Class<LiveOrdersChannel.Message> getMessageType() {
    return LiveOrdersChannel.Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<LiveOrdersChannel.Message> {

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
    public Integer order_type;
    public Long datetime;
    public Long microtimestamp;
  }
}
