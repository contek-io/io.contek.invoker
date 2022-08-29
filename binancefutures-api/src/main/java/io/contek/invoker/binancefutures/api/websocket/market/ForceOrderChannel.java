package io.contek.invoker.binancefutures.api.websocket.market;

import io.contek.invoker.binancefutures.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancefutures.api.websocket.common.WebSocketStreamMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.common.constants.WebSocketChannelKeys._forceOrder;

@ThreadSafe
public final class ForceOrderChannel
    extends MarketWebSocketChannel<ForceOrderChannel.Id, ForceOrderChannel.Message> {

  ForceOrderChannel(ForceOrderChannel.Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return ForceOrderChannel.Message.class;
  }

  @Immutable
  public static final class Id extends MarketWebSocketChannelId<ForceOrderChannel.Message> {

    private Id(String symbol) {
      super(symbol, _forceOrder);
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<ForceOrderEvent> {}
}
