package io.contek.invoker.hbdmlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _OrderIdentifier {

  public long order_id;
  public String order_id_str;
  public Long client_order_id;

  @Override
  public String toString() {
    return "_OrderIdentifier{" +
            "order_id=" + order_id +
            ", order_id_str='" + order_id_str + '\'' +
            ", client_order_id=" + client_order_id +
            '}';
  }
}
