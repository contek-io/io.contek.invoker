package io.contek.invoker.bitstamp.api.websocket.market;

import io.contek.invoker.bitstamp.api.websocket.WebSocketChannel;
import io.contek.invoker.bitstamp.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.bitstamp.api.websocket.market.LiveTradesChannel.Message;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class LiveTradesChannel extends WebSocketChannel<Message> {

  public static final String PREFIX = "live_trades_";

  LiveTradesChannel(String currencyPair) {
    super(PREFIX + currencyPair);
  }

  @Override
  protected Class<Message> getMessageType() {
    return Message.class;
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
  }
}
