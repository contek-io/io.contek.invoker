package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketStreamMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.common.constants.WebSocketChannelKeys._depth;

@ThreadSafe
public final class DepthDiffChannel
    extends MarketWebSocketChannel<DepthDiffChannel.Id, DepthDiffChannel.Message> {

  DepthDiffChannel(DepthDiffChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<DepthDiffChannel.Message> getMessageType() {
    return DepthDiffChannel.Message.class;
  }

  @Immutable
  public static final class Id extends MarketWebSocketChannelId<DepthDiffChannel.Message> {

    private Id(String symbol, String interval) {
      super(symbol, _depth + "@" + interval);
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<DepthUpdateEvent> {}
}
