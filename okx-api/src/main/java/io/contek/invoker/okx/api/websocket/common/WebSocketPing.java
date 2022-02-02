package io.contek.invoker.okx.api.websocket.common;

import io.contek.invoker.commons.websocket.IWebSocketRawTextMessage;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketOutboundKeys._ping;

@NotThreadSafe
public final class WebSocketPing extends WebSocketOutboundMessage
    implements IWebSocketRawTextMessage {

  @Override
  public String getRawText() {
    return _ping;
  }
}
