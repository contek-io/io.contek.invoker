package io.contek.invoker.bybit.api.websocket;

import io.contek.invoker.commons.websocket.BaseWebSocketChannelId;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class WebSocketChannelId extends BaseWebSocketChannelId {

  protected WebSocketChannelId(String topic) {
    super(topic);
  }

  public final String getTopic() {
    return getValue();
  }
}
