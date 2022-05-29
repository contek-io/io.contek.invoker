package io.contek.invoker.okx.api.websocket.common;

public final class WebSocketLoginRequest extends WebSocketRequest<WebSocketLoginRequest.Arg> {

  public static final class Arg {
    public String apiKey;
    public String passphrase;
    public String timestamp;
    public String sign;
  }
}
