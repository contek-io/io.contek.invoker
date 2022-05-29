package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketStreamMessage;

public final class ForceOrderChannel
    extends MarketWebSocketChannel<ForceOrderChannel.Id, ForceOrderChannel.Message> {

  ForceOrderChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends MarketWebSocketChannelId<Message> {

    private Id(String symbol) {
      super(symbol, "forceOrder");
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }
  }

  public static final class Message extends WebSocketStreamMessage<ForceOrderEvent> {}
}
