package io.contek.invoker.bitmex.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketOperationRequest;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketSubscribeResponse;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;
import io.contek.invoker.bitmex.api.websocket.common.WebSocketUnsubscribeResponse;
import io.contek.invoker.commons.websocket.*;

import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys._subscribe;
import static io.contek.invoker.bitmex.api.websocket.common.constants.WebSocketRequestOperationKeys._unsubscribe;
import static io.contek.invoker.commons.websocket.SubscriptionState.*;

@ThreadSafe
public abstract class WebSocketChannel<Message extends WebSocketTableDataMessage<Data>, Data>
    extends BaseWebSocketChannel<WebSocketChannelId<Message>, Message, List<Data>> {

  protected WebSocketChannel(WebSocketChannelId<Message> id) {
    super(id);
  }

  @Override
  protected final List<Data> getData(Message message) {
    return message.data;
  }

  @Override
  protected final SubscriptionState subscribe(WebSocketSession session) {
    WebSocketChannelId<Message> id = getId();
    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = _subscribe;
    request.args = ImmutableList.of(id.getTopic());
    session.send(request);
    return SUBSCRIBING;
  }

  @Override
  protected final SubscriptionState unsubscribe(WebSocketSession session) {
    WebSocketChannelId<Message> id = getId();
    WebSocketOperationRequest request = new WebSocketOperationRequest();
    request.op = _unsubscribe;
    request.args = ImmutableList.of(id.getTopic());
    session.send(request);
    return UNSUBSCRIBING;
  }

  @Nullable
  @Override
  protected final SubscriptionState getState(AnyWebSocketMessage message) {
    if (message instanceof WebSocketSubscribeResponse confirmation) {
      if (!confirmation.success) {
        throw new WebSocketIllegalMessageException(confirmation.ret_msg);
      }
      WebSocketChannelId<Message> id = getId();
      if (confirmation.subscribe.equals(id.getTopic())) {
        return SUBSCRIBED;
      }
    }

    if (message instanceof WebSocketUnsubscribeResponse confirmation) {
      if (!confirmation.success) {
        throw new WebSocketIllegalMessageException(confirmation.ret_msg);
      }
      WebSocketChannelId<Message> id = getId();
      if (confirmation.unsubscribe.equals(id.getTopic())) {
        reset();
        return UNSUBSCRIBED;
      }
    }
    return null;
  }

  @Override
  protected final void reset() {}
}
