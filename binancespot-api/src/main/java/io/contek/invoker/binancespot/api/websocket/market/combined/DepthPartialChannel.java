package io.contek.invoker.binancespot.api.websocket.market.combined;

import io.contek.invoker.binancespot.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancespot.api.websocket.market.DepthPartialEvent;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancespot.api.websocket.common.constants.WebSocketChannelKeys.depthPartial;

@ThreadSafe
public final class DepthPartialChannel
    extends MarketCombinedChannel<DepthPartialChannel.Message, DepthPartialEvent> {

  DepthPartialChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
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
  public static final class Message extends WebSocketStreamMessage<DepthPartialEvent> {}
}
