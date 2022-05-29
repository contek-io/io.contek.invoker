package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketStreamMessage;

public final class ForceOrderChannel
    extends MarketWebSocketChannel<ForceOrderChannel.Id, ForceOrderChannel.Message> {

  ForceOrderChannel(ForceOrderChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return ForceOrderChannel.Message.class;
  }

  public static final class Id extends MarketWebSocketChannelId<ForceOrderChannel.Message> {

    private Id(String symbol) {
      super(symbol, "forceOrder");
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }
  }

  public static final class Message extends WebSocketStreamMessage<ForceOrderEvent> {}
}
