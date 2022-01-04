package io.contek.invoker.deribit.api.websocket.common;

import io.contek.invoker.deribit.api.common._Error;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class WebSocketResponse extends WebSocketInboundMessage {

  public Integer id;
  public String jsonrpc;
  public AuthResponseParams result;
  public _Error error;

  @NotThreadSafe
  public static final class AuthResponseParams {

    public String access_token;
    public Long expires_in;
    public String refresh_token;
    public String scope;
    public String state;
    public String token_type;
  }
}
