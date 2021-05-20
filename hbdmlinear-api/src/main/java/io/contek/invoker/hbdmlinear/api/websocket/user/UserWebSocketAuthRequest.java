package io.contek.invoker.hbdmlinear.api.websocket.user;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public final class UserWebSocketAuthRequest extends UserWebSocketRequest {

  public String type;
  public String AccessKeyId;
  public String SignatureMethod;
  public String SignatureVersion;
  public String Timestamp;
  public String Signature;
}
