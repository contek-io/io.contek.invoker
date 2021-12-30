package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketSessionIdleException extends WebSocketRuntimeException {

  public WebSocketSessionIdleException() {}

  public WebSocketSessionIdleException(String message) {
    super(message);
  }

  public WebSocketSessionIdleException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketSessionIdleException(Throwable cause) {
    super(cause);
  }
}
