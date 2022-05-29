package io.contek.invoker.hbdminverse.api.websocket.common.notification;

public final class NotificationWebSocketAuthRequest extends NotificationWebSocketRequest {

  public String type;
  public String AccessKeyId;
  public String SignatureMethod;
  public String SignatureVersion;
  public String Timestamp;
  public String Signature;
}
