package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancespot.api.websocket.common.WebSocketStreamMessage;

public final class AggTradeChannel
    extends MarketWebSocketChannel<AggTradeChannel.Id, AggTradeChannel.Message> {

  AggTradeChannel(AggTradeChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends MarketWebSocketChannelId<Message> {

    private Id(String symbol) {
      super(symbol, "aggTrade");
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }
  }

  public static final class Message extends WebSocketStreamMessage<AggTradeEvent> {}
}
