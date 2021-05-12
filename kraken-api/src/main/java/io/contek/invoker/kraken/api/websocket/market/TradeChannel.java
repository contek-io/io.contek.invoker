package io.contek.invoker.kraken.api.websocket.market;

import io.contek.invoker.kraken.api.common._Trade;
import io.contek.invoker.kraken.api.websocket.WebSocketChannel;
import io.contek.invoker.kraken.api.websocket.WebSocketChannelId;
import io.contek.invoker.kraken.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelDataMessage;
import io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys._book;

@ThreadSafe
public final class TradeChannel extends WebSocketChannel<TradeChannel.Id, TradeChannel.Message> {

  TradeChannel(Id id, WebSocketRequestIdGenerator requestIdGenerator) {
    super(id, requestIdGenerator);
  }

  @Override
  public Class<TradeChannel.Message> getMessageType() {
    return TradeChannel.Message.class;
  }

  @Override
  protected Subscription getSubscription() {
    Subscription subscription = new Subscription();
    subscription.name = WebSocketChannelKeys._trade;
    return subscription;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<TradeChannel.Message> {

    private Id(String pair) {
      super(_book, pair);
    }

    public static Id of(String pair) {
      return new Id(pair);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketChannelDataMessage<List<_Trade>> {}
}
