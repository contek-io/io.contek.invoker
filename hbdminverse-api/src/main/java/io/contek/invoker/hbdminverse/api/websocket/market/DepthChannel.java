package io.contek.invoker.hbdminverse.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdminverse.api.common._Depth;
import io.contek.invoker.hbdminverse.api.websocket.common.marketdata.*;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static java.lang.String.format;

public final class DepthChannel
    extends MarketDataMarketWebSocketChannel<DepthChannel.Id, DepthChannel.Message> {

  DepthChannel(Id id, MarketDataWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, Message.class, requestIdGenerator);
  }

  @Override
  protected SubscriptionState subscribe(WebSocketSession session) {
    Id id = getId();
    WebSocketSubscribeMarketDepthRequest request = new WebSocketSubscribeMarketDepthRequest();
    request.sub = id.getChannel();
    request.id = generateNexRequestId();
    session.send(request);
    return SUBSCRIBING;
  }

  public static final class Id extends MarketDataWebSocketChannelId<Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String contractCode, String type) {
      return new Id(format("market.%s.depth.%s", contractCode, type));
    }
  }

  public static final class Message extends MarketDataWebSocketTickMessage<_Depth> {}

  public static final class WebSocketSubscribeMarketDepthRequest
      extends MarketDataWebSocketSubscribeRequest {}
}
