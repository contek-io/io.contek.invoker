package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.ftx.api.common._OrderBook;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._orderbook;

public final class OrderBookChannel
    extends WebSocketMarketChannel<OrderBookChannel.Id, OrderBookChannel.Message> {

  OrderBookChannel(Id id) {
    super(id);
  }

  @Override
  public Class<OrderBookChannel.Message> getMessageType() {
    return OrderBookChannel.Message.class;
  }

  public static final class Id extends WebSocketMarketChannelId<OrderBookChannel.Message> {

    private Id(String market) {
      super(_orderbook, market);
    }

    public static OrderBookChannel.Id of(String market) {
      return new OrderBookChannel.Id(market);
    }
  }

  public static final class Data extends _OrderBook {

    public String action;

    public Long checksum;

    public Double time;
  }

  public static final class Message extends WebSocketMarketChannelMessage<Data> {}
}
