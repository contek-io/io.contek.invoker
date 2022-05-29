package io.contek.invoker.kraken.api.websocket.market;

import io.contek.invoker.kraken.api.common._Trade;
import io.contek.invoker.kraken.api.websocket.WebSocketChannel;
import io.contek.invoker.kraken.api.websocket.WebSocketChannelId;
import io.contek.invoker.kraken.api.websocket.WebSocketRequestIdGenerator;
import io.contek.invoker.kraken.api.websocket.common.Subscription;
import io.contek.invoker.kraken.api.websocket.common.WebSocketChannelDataMessage;

import java.util.List;

import static io.contek.invoker.kraken.api.websocket.common.constants.WebSocketChannelKeys._trade;

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
    subscription.name = _trade;
    return subscription;
  }

  public static final class Id extends WebSocketChannelId<TradeChannel.Message> {

    private Id(String pair) {
      super(_trade, pair);
    }

    public static Id of(String pair) {
      return new Id(pair);
    }
  }

  public static final class Message extends WebSocketChannelDataMessage<List<_Trade>> {}
}
