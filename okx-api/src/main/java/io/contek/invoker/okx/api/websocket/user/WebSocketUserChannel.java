package io.contek.invoker.okx.api.websocket.user;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.okx.api.websocket.WebSocketChannel;
import io.contek.invoker.okx.api.websocket.WebSocketChannelId;
import io.contek.invoker.okx.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.okx.api.websocket.common.WebSocketSubscriptionRequest;
import io.contek.invoker.okx.api.websocket.common.constants.WebSocketOutboundKeys;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBING;

@ThreadSafe
public abstract class WebSocketUserChannel<
        Id extends WebSocketChannelId<Message>, Message extends WebSocketChannelMessage<?>>
    extends WebSocketChannel<Id, Message> {

  protected WebSocketUserChannel(Id id) {
    super(id);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    Id id = getId();
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = WebSocketOutboundKeys._subscribe;
    request.channel = id.getChannel();
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    Id id = getId();
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = WebSocketOutboundKeys._unsubscribe;
    request.channel = getId().getChannel();
    session.send(request);
    return UNSUBSCRIBING;
  }
}
