package io.contek.invoker.bitmex.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _OrderBookLevel {

  public String symbol;
  public Long id;
  public String side;
  public Double size;
  public Double price;

  @Override
  public String toString() {
    return "_OrderBookLevel{" +
            "symbol='" + symbol + '\'' +
            ", id=" + id +
            ", side='" + side + '\'' +
            ", size=" + size +
            ", price=" + price +
            '}';
  }
}
