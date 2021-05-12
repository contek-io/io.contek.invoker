package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketStreamMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class DepthUpdateChannel
    extends MarketWebSocketChannel<DepthUpdateChannel.Id, DepthUpdateChannel.Message> {

  DepthUpdateChannel(DepthUpdateChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<DepthUpdateChannel.Message> getMessageType() {
    return DepthUpdateChannel.Message.class;
  }

  @Immutable
  public static final class Id extends MarketWebSocketChannelId<DepthUpdateChannel.Message> {

    private Id(String symbol, String interval) {
      super(symbol, "depth@" + interval);
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<DepthUpdateEvent> {}
}
