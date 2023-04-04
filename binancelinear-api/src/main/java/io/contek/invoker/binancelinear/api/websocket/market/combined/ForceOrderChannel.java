package io.contek.invoker.binancelinear.api.websocket.market.combined;

import io.contek.invoker.binancelinear.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancelinear.api.websocket.market.ForceOrderEvent;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancelinear.api.websocket.common.constants.WebSocketChannelKeys.forceOrder;

@ThreadSafe
public final class ForceOrderChannel
    extends MarketCombinedChannel<ForceOrderChannel.Message, ForceOrderEvent> {

  ForceOrderChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return ForceOrderChannel.Message.class;
  }

  @Immutable
  public static final class Id extends MarketCombinedChannelId<Message> {

    private Id(String symbol) {
      super(forceOrder(symbol));
    }

    public static Id of(String symbol) {
      return new Id(symbol);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<ForceOrderEvent> {}
}
