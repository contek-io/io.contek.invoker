package io.contek.invoker.bybit.api.websocket.market;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class TickerChannel extends WebSocketChannel<TickerChannel.Message> {

  TickerChannel(TickerChannel.Id id) {
    super(id);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String symbol) {
      return new Id(String.format("instrument_info.100ms.%s", symbol));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage<Ticker> {
    public Long cs;
  }

  @NotThreadSafe
  public static final class Ticker {

    public String symbol;
    public String tickDirection;
    public String price24hPcnt;
    public String lastPrice;
    public String prevPrice24h;
    public String highPrice24h;
    public String lowPrice24h;
    public String prevPrice1h;
    public String markPrice;
    public String indexPrice;
    public String openInterest;
    public String openInterestValue;
    public String turnover24h;
    public String volume24h;
    public String nextFundingTime;
    public String fundingRate;
    public String bid1Price;
    public String bid1Size;
    public String ask1Price;
    public String ask1Size;
  }
}
