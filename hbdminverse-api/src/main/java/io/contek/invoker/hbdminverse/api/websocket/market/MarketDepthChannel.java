package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdminverse.api.common._Depth;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;

@ThreadSafe
public final class MarketDepthChannel extends WebSocketMarketChannel<MarketDepthChannel.Message> {

  MarketDepthChannel(
      String contractCode, String type, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super(
        String.format("market.%s.depth.%s", contractCode, type),
        MarketDepthChannel.Message.class,
        requestIdGenerator);
  }

  @Override
  protected SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscribeMarketDepthRequest request = new WebSocketSubscribeMarketDepthRequest();
    request.sub = getTopic();
    request.id = generateNextId();
    session.send(request);
    return SUBSCRIBING;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<_Depth> {}
}
