package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketIllegalSequenceException extends WebSocketRuntimeException {

  public WebSocketIllegalSequenceException(String message) {
    super(message);
  }

  public WebSocketIllegalSequenceException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketIllegalSequenceException(Throwable cause) {
    super(cause);
  }
}
