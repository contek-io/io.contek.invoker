package io.contek.invoker.bybitlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _ApiKey {

  public String api_key;
  public String type;
  public Long user_id;
  public Long inviter_id;
  public List<String> ips;
  public String note;
  public List<String> permissions;
  public String created_at;
  public String expired_at;
  public boolean read_only;
}
