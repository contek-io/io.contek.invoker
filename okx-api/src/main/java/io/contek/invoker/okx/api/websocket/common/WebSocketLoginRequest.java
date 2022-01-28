package io.contek.invoker.okx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketLoginRequest extends WebSocketRequest<WebSocketLoginRequest.Arg> {

  @NotThreadSafe
  public static final class Arg {
    public String apiKey;
    public String passphrase;
    public String timestamp;
    public String sign;
  }
}
