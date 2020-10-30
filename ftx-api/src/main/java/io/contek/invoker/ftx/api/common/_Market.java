package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Market {

  public String name;
  public String baseCurrency;
  public String quoteCurrency;
  public String type;
  public String underlying;
  public Boolean enabled;
  public Double ask;
  public Double bid;
  public Double last;
  public Boolean postOnly;
  public Double priceIncrement;
  public Double sizeIncrement;
  public Boolean restricted;
}
