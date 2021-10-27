package io.contek.invoker.ftx.api.websocket.market;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionRequest;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBING;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys._subscribe;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys._unsubscribe;

@ThreadSafe
public abstract class WebSocketMarketChannel<
        Id extends WebSocketMarketChannelId<Message>,
        Message extends WebSocketMarketChannelMessage<?>>
    extends WebSocketChannel<Id, Message> {

  protected WebSocketMarketChannel(Id id) {
    super(id);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    Id id = getId();
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = _subscribe;
    request.channel = id.getChannel();
    request.market = id.getMarket();
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    Id id = getId();
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = _unsubscribe;
    request.channel = getId().getChannel();
    request.market = getId().getMarket();
    session.send(request);
    return UNSUBSCRIBING;
  }
}
