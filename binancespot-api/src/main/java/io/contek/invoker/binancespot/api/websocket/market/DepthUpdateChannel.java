package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancespot.api.websocket.common.WebSocketStreamMessage;

public final class DepthUpdateChannel
    extends MarketWebSocketChannel<DepthUpdateChannel.Id, DepthUpdateChannel.Message> {

  DepthUpdateChannel(DepthUpdateChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<DepthUpdateChannel.Message> getMessageType() {
    return DepthUpdateChannel.Message.class;
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
