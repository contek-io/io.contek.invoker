package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.commons.websocket.SubscriptionState;
import io.contek.invoker.commons.websocket.WebSocketSession;
import io.contek.invoker.ftx.api.websocket.WebSocketChannel;
import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;
import io.contek.invoker.ftx.api.websocket.common.WebSocketSubscriptionRequest;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.commons.websocket.SubscriptionState.SUBSCRIBING;
import static io.contek.invoker.commons.websocket.SubscriptionState.UNSUBSCRIBING;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys._subscribe;
import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketOutboundKeys._unsubscribe;

@ThreadSafe
public abstract class WebSocketUserChannel<Message extends WebSocketChannelMessage<Data>, Data>
    extends WebSocketChannel<WebSocketUserChannelId<Message>, Message, Data> {

  protected WebSocketUserChannel(WebSocketUserChannelId<Message> id) {
    super(id);
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketUserChannelId<Message> id = getId();
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = _subscribe;
    request.channel = id.getChannel();
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketUserChannelId<Message> id = getId();
    WebSocketSubscriptionRequest request = new WebSocketSubscriptionRequest();
    request.op = _unsubscribe;
    request.channel = getId().getChannel();
    session.send(request);
    return UNSUBSCRIBING;
  }
}
