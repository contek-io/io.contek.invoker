package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.common._TradeDetail;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeTradeDetailRequest;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;

@ThreadSafe
public final class TradeDetailChannel extends WebSocketMarketChannel<TradeDetailChannel.Message> {

  TradeDetailChannel(String contractCode, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super("market." + contractCode + ".trade.detail", Message.class, requestIdGenerator);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscribeTradeDetailRequest request = new WebSocketSubscribeTradeDetailRequest();
    request.sub = getTopic();
    request.id = generateNextId();
    session.send(request);
    return SUBSCRIBING;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<_TradeDetail> {}
}
