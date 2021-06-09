package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import com.google.gson.annotations.SerializedName;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class NotificationWebSocketResponse<T> extends NotificationWebSocketInboundMessage {

  public String cid;

  @SerializedName("err-code")
  public int err_code;

  @SerializedName("err-msg")
  public String err_msg;

  public T data;
}
