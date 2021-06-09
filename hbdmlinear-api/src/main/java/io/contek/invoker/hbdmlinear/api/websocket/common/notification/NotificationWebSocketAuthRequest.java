package io.contek.invoker.hbdmlinear.api.websocket.common.notification;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class NotificationWebSocketAuthRequest extends NotificationWebSocketRequest {

  public String type;
  public String AccessKeyId;
  public String SignatureMethod;
  public String SignatureVersion;
  public String Timestamp;
  public String Signature;
}
