package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

@NotThreadSafe
public class _Trade implements Serializable {

  public Long id;
  public Boolean liquidation;
  public Double price;
  public String side;
  public Double size;
  public String time;

  @Override
  public String toString() {
    return "_Trade{"
        + "id="
        + id
        + ", liquidation="
        + liquidation
        + ", price="
        + price
        + ", side='"
        + side
        + '\''
        + ", size="
        + size
        + ", time='"
        + time
        + '\''
        + '}';
  }
}
