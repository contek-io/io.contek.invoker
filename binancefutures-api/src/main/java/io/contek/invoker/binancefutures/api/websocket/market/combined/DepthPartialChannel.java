package io.contek.invoker.binancefutures.api.websocket.market.combined;

import io.contek.invoker.binancefutures.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancefutures.api.websocket.market.DepthUpdateEvent;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.common.constants.WebSocketChannelKeys.depthPartial;

@ThreadSafe
public final class DepthPartialChannel
    extends MarketCombinedChannel<DepthPartialChannel.Message, DepthUpdateEvent> {

  DepthPartialChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<DepthPartialChannel.Message> getMessageType() {
    return DepthPartialChannel.Message.class;
  }

  @Immutable
  public static final class Id extends MarketCombinedChannelId<Message> {

    private Id(String symbol, int levels, @Nullable String interval) {
      super(depthPartial(symbol, levels, interval));
    }

    public static Id of(String symbol, int levels, @Nullable String interval) {
      return new Id(symbol, levels, interval);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<DepthUpdateEvent> {}
}
