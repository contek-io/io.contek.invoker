package io.contek.invoker.okx.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Trade {

  public long id;
  public boolean liquidation;
  public double price;
  public String side;
  public double size;
  public String time;
}
