package io.contek.invoker.okx.api.websocket.common;

import io.contek.invoker.commons.websocket.IWebSocketRawTextMessage;

import static io.contek.invoker.commons.websocket.constants.WebSocketPingPongKeys._pong;

public final class WebSocketPong extends WebSocketInboundMessage
    implements IWebSocketRawTextMessage {

  @Override
  public String getRawText() {
    return _pong;
  }
}
