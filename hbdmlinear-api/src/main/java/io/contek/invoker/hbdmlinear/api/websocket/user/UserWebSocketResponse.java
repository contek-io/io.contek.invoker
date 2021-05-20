package io.contek.invoker.hbdmlinear.api.websocket.user;

import com.google.gson.annotations.SerializedName;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class UserWebSocketResponse<T> extends UserWebSocketInboundMessage {

  public String cid;

  @SerializedName("err-code")
  public int err_code;

  @SerializedName("err-msg")
  public String err_msg;

  public T data;
}
