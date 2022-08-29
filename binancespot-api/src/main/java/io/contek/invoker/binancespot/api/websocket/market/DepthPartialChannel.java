package io.contek.invoker.binancespot.api.websocket.market;

import io.contek.invoker.binancespot.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancespot.api.websocket.common.WebSocketStreamMessage;

import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancespot.api.websocket.common.constants.WebSocketChannelKeys.*;
import static java.lang.String.format;

@ThreadSafe
public final class DepthPartialChannel
    extends MarketWebSocketChannel<DepthPartialChannel.Id, DepthPartialChannel.Message> {

  DepthPartialChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends MarketWebSocketChannelId<Message> {

    private Id(String symbol, String depthLevels, String interval) {
      super(symbol, depthLevels + "@" + interval);
    }

    private Id(String symbol, String depthLevels) {
      super(symbol, depthLevels);
    }

    public static Id of(String symbol, int levels, @Nullable String interval) {
      String depthLevel = switch(levels) {
        case 5 -> _depth5;
        case 10 -> _depth10;
        case 20 -> _depth20;
        default -> throw new IllegalArgumentException(format("Unsupported levels: %d", levels));
      };
      return interval == null || interval.isEmpty()
          ? new Id(symbol, depthLevel)
          : new Id(symbol, depthLevel, interval);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<DepthUpdateEvent> {}
}
