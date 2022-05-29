package io.contek.invoker.hbdmlinear.api.common;

import java.util.List;

public class _TradeDetail {

  public List<_Trade> data;
  public long id;
  public long ts;

  @Override
  public String toString() {
    return "_TradeDetail{" +
            "data=" + data +
            ", id=" + id +
            ", ts=" + ts +
            '}';
  }
}
