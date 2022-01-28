package io.contek.invoker.okx.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Order {

  public String createdAt;
  public Double filledSize;
  public String future;
  public Long id;
  public String market;
  public Double price;
  public Double avgFillPrice;
  public Double remainingSize;
  public String side;
  public Double size;
  public String status;
  public String type;
  public Boolean reduceOnly;
  public Boolean ioc;
  public Boolean postOnly;
  public String clientId;
}
