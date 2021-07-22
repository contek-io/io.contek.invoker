package io.contek.invoker.bybit.api.common;

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

  @Override
  public String toString() {
    return "_ApiKey{"
        + "api_key='"
        + api_key
        + '\''
        + ", type='"
        + type
        + '\''
        + ", user_id="
        + user_id
        + ", inviter_id="
        + inviter_id
        + ", ips="
        + ips
        + ", note='"
        + note
        + '\''
        + ", permissions="
        + permissions
        + ", created_at='"
        + created_at
        + '\''
        + ", expired_at='"
        + expired_at
        + '\''
        + ", read_only="
        + read_only
        + '}';
  }
}
