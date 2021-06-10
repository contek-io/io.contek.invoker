package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdminverse.api.common._TradeDetail;
import io.contek.invoker.hbdminverse.api.websocket.common.marketdata.*;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static java.lang.String.format;

@ThreadSafe
public final class TradeDetailChannel
    extends MarketDataMarketWebSocketChannel<TradeDetailChannel.Id, TradeDetailChannel.Message> {

  TradeDetailChannel(Id id, MarketDataWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, Message.class, requestIdGenerator);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    Id id = getId();
    SubscribeTradeDetailRequest request = new SubscribeTradeDetailRequest();
    request.sub = id.getChannel();
    request.id = generateNexRequestId();
    session.send(request);
    return SUBSCRIBING;
  }

  @Immutable
  public static final class Id extends MarketDataWebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String contractCode) {
      return new Id(format("market." + contractCode + ".trade.detail", contractCode));
    }
  }

  @NotThreadSafe
  public static final class Message extends MarketDataWebSocketTickMessage<_TradeDetail> {}

  @NotThreadSafe
  public static final class SubscribeTradeDetailRequest
      extends MarketDataWebSocketSubscribeRequest {

    public Integer size;
  }
}
