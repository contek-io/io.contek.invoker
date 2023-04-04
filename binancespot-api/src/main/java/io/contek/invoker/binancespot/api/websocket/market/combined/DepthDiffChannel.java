package io.contek.invoker.binancespot.api.websocket.market.combined;

import io.contek.invoker.binancespot.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancespot.api.websocket.market.DepthDiffEvent;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancespot.api.websocket.common.constants.WebSocketChannelKeys.depth;

@ThreadSafe
public final class DepthDiffChannel
    extends MarketCombinedChannel<DepthDiffChannel.Message, DepthDiffEvent> {

  DepthDiffChannel(DepthDiffChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
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
  public static final class Message extends WebSocketStreamMessage<DepthDiffEvent> {}
}
