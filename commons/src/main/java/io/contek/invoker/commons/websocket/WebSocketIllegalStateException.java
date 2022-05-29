package io.contek.invoker.commons.websocket;

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
