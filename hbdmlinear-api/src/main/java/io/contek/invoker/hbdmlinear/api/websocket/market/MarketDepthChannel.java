package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.common._Depth;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeMarketDepthRequest;
import io.contek.invoker.hbdmlinear.api.websocket.common.WebSocketSubscribeRequest;

import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;

@ThreadSafe
public final class MarketDepthChannel extends WebSocketMarketChannel<MarketDepthChannel.Message> {

  MarketDepthChannel(
      String contractCode, String type, WebSocketMarketRequestIdGenerator requestIdGenerator) {
    super(
        String.format("market.%s.depth.%s", contractCode, type), Message.class, requestIdGenerator);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketSubscribeRequest request = new WebSocketSubscribeMarketDepthRequest();
    request.sub = getTopic();
    request.id = generateNextId();
    session.send(request);
    return SUBSCRIBING;
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<_Depth> {}
}
