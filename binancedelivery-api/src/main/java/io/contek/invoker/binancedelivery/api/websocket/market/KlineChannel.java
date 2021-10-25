package io.contek.invoker.binancedelivery.api.websocket.market;

import io.contek.invoker.binancedelivery.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketStreamMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class KlineChannel
    extends MarketWebSocketChannel<KlineChannel.Id, KlineChannel.Message> {

  KlineChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends MarketWebSocketChannelId<Message> {

    private Id(String symbol, String interval) {
      super(symbol, "kline_" + interval);
    }

    public static Id of(String symbol, String interval) {
      return new Id(symbol, interval);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketStreamMessage<KlineEvent> {}
}
