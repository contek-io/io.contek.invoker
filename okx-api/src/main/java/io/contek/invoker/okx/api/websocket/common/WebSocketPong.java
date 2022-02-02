package io.contek.invoker.okx.api.websocket.common;

import io.contek.invoker.commons.websocket.IWebSocketRawTextMessage;

import javax.annotation.concurrent.NotThreadSafe;

import static io.contek.invoker.okx.api.websocket.common.constants.WebSocketInboundKeys._pong;

@NotThreadSafe
public final class WebSocketPong extends WebSocketInboundMessage
    implements IWebSocketRawTextMessage {

  @Override
  public String getRawText() {
    return _pong;
  }
}
