package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.common._TradeDetail;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeTradeDetailRequest;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static java.lang.String.format;

@ThreadSafe
public final class TradeDetailChannel
    extends MarketWebSocketChannel<TradeDetailChannel.Id, TradeDetailChannel.Message> {

  TradeDetailChannel(
      TradeDetailChannel.Id id, MarketWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, Message.class, requestIdGenerator);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    TradeDetailChannel.Id id = getId();
    WebSocketSubscribeTradeDetailRequest request = new WebSocketSubscribeTradeDetailRequest();
    request.sub = id.getChannel();
    request.id = generateNexRequestId();
    session.send(request);
    return SUBSCRIBING;
  }

  @Immutable
  public static final class Id extends MarketWebSocketChannelId<TradeDetailChannel.Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String contractCode) {
      return new Id(format("market." + contractCode + ".trade.detail", contractCode));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<_TradeDetail> {}
}
