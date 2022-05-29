package io.contek.invoker.commons.websocket;

public final class WebSocketSessionInactiveException extends WebSocketRuntimeException {

  public WebSocketSessionInactiveException() {}

  public WebSocketSessionInactiveException(String message) {
    super(message);
  }

  public WebSocketSessionInactiveException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketSessionInactiveException(Throwable cause) {
    super(cause);
  }
}
