package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketStreamMessage;

public final class DepthUpdateChannel
    extends MarketWebSocketChannel<DepthUpdateChannel.Id, DepthUpdateChannel.Message> {

  DepthUpdateChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends MarketWebSocketChannelId<Message> {

    private Id(String symbol, String interval) {
      super(symbol, "depth@" + interval);
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }
  }

  public static final class Message extends WebSocketStreamMessage<DepthUpdateEvent> {}
}
