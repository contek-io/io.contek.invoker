package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketIllegalStateException extends WebSocketRuntimeException {

  public WebSocketIllegalStateException(String message) {
    super(message);
  }

  public WebSocketIllegalStateException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketIllegalStateException(Throwable cause) {
    super(cause);
  }
}
