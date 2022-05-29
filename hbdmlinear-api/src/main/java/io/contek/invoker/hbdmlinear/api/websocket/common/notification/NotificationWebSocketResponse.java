package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import com.google.gson.annotations.SerializedName;

public abstract class NotificationWebSocketResponse extends NotificationWebSocketInboundMessage {

  public String cid;

  @SerializedName("err-code")
  public int err_code;

  @SerializedName("err-msg")
  public String err_msg;
}
