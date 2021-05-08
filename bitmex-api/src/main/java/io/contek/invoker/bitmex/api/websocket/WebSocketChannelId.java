package io.contek.invoker.bitmex.api.websocket;

import io.contek.invoker.bitmex.api.websocket.common.WebSocketTableDataMessage;
import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class WebSocketChannelId<Message extends WebSocketTableDataMessage<?>>
    extends BaseWebSocketChannelId<Message> {

  protected WebSocketChannelId(String topic) {
    super(topic);
  }

  public final String getTopic() {
    return getValue();
  }
}
