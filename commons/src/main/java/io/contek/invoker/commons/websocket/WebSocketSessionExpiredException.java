package io.contek.invoker.commons.websocket;

public final class WebSocketSessionExpiredException extends WebSocketRuntimeException {

  public WebSocketSessionExpiredException() {}

  public WebSocketSessionExpiredException(String message) {
    super(message);
  }

  public WebSocketSessionExpiredException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketSessionExpiredException(Throwable cause) {
    super(cause);
  }
}
