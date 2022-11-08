package io.contek.invoker.binancelinear.api.websocket.market.combined;

import io.contek.invoker.binancelinear.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancelinear.api.websocket.market.DepthUpdateEvent;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancelinear.api.websocket.common.constants.WebSocketChannelKeys.depth;

@ThreadSafe
public final class DepthDiffChannel
    extends MarketCombinedChannel<DepthDiffChannel.Message, DepthUpdateEvent> {

  DepthDiffChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<DepthDiffChannel.Message> getMessageType() {
    return DepthDiffChannel.Message.class;
  }

  @Immutable
  public static final class Id extends MarketCombinedChannelId<Message> {

    private Id(String symbol, String interval) {
      super(depth(symbol, interval));
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<DepthUpdateEvent> {}
}
