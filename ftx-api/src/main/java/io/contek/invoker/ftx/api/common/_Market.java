package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _Market implements Serializable {

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

  @Override
  public String toString() {
    return "_Market{"
        + "name='"
        + name
        + '\''
        + ", baseCurrency='"
        + baseCurrency
        + '\''
        + ", quoteCurrency='"
        + quoteCurrency
        + '\''
        + ", type='"
        + type
        + '\''
        + ", underlying='"
        + underlying
        + '\''
        + ", enabled="
        + enabled
        + ", ask="
        + ask
        + ", bid="
        + bid
        + ", last="
        + last
        + ", postOnly="
        + postOnly
        + ", priceIncrement="
        + priceIncrement
        + ", sizeIncrement="
        + sizeIncrement
        + ", restricted="
        + restricted
        + '}';
  }
}
