package io.contek.invoker.hbdmlinear.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.hbdmlinear.api.common._Depth;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static java.lang.String.format;

@ThreadSafe
public final class DepthChannel
    extends MarketWebSocketChannel<DepthChannel.Id, DepthChannel.Message> {

  DepthChannel(DepthChannel.Id id, MarketWebSocketRequestIdGenerator requestIdGenerator) {
    super(id, Message.class, requestIdGenerator);
  }

  @Override
  protected SubscriptionState subscribe(WebSocketSession session) {
    DepthChannel.Id id = getId();
    WebSocketSubscribeMarketDepthRequest request = new WebSocketSubscribeMarketDepthRequest();
    request.sub = id.getChannel();
    request.id = generateNexRequestId();
    session.send(request);
    return SUBSCRIBING;
  }

  @Immutable
  public static final class Id extends MarketWebSocketChannelId<DepthChannel.Message> {

    private Id(String topic) {
      super(topic);
    }

    public static Id of(String contractCode, String type) {
      return new Id(format("market.%s.depth.%s", contractCode, type));
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTickMessage<_Depth> {}

  @NotThreadSafe
  public static final class WebSocketSubscribeMarketDepthRequest
      extends MarketWebSocketSubscribeRequest {}
}
