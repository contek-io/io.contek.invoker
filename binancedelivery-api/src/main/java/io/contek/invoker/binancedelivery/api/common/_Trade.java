package io.contek.invoker.binancedelivery.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Trade {

  public Long id;
  public Double price;
  public Double qty;
  public Double baseQty;
  public Long time;
  public Boolean isBuyerMaker;
}
