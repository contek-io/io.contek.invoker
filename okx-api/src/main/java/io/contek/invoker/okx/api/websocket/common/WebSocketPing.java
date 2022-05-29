package io.contek.invoker.okx.api.websocket.common;

import io.contek.invoker.commons.websocket.IWebSocketRawTextMessage;

import static io.contek.invoker.commons.websocket.constants.WebSocketPingPongKeys._ping;

public final class WebSocketPing extends WebSocketOutboundMessage
    implements IWebSocketRawTextMessage {

  @Override
  public String getRawText() {
    return _ping;
  }
}
