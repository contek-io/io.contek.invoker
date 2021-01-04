package io.contek.invoker.commons.websocket;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class WebSocketIllegalMessageException extends WebSocketRuntimeException {

  public WebSocketIllegalMessageException() {}

  public WebSocketIllegalMessageException(String message) {
    super(message);
  }

  public WebSocketIllegalMessageException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebSocketIllegalMessageException(Throwable cause) {
    super(cause);
  }
}
