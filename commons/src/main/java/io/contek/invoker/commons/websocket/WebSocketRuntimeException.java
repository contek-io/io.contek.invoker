package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.Immutable;

@Immutable
public abstract class WebSocketRuntimeException extends RuntimeException {

  public WebSocketRuntimeException() {}

  public WebSocketRuntimeException(String message) {
    super(message);
  }

  public WebSocketRuntimeException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketRuntimeException(Throwable cause) {
    super(cause);
  }
}
