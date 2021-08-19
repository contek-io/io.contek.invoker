package io.contek.invoker.hbdminverse.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
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
