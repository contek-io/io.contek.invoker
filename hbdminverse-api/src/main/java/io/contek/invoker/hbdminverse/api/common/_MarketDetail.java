package io.contek.invoker.hbdminverse.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _MarketDetail {

  public long id;
  public long ts;
  public _DepthLevel ask;
  public _DepthLevel bid;
  public String contract_code;
  public String open;
  public String close;
  public String low;
  public String high;
  public String amount;
  public int count;
  public String vol;

  @Override
  public String toString() {
    return "_MarketDetail{" +
            "id=" + id +
            ", ts=" + ts +
            ", ask=" + ask +
            ", bid=" + bid +
            ", contract_code='" + contract_code + '\'' +
            ", open='" + open + '\'' +
            ", close='" + close + '\'' +
            ", low='" + low + '\'' +
            ", high='" + high + '\'' +
            ", amount='" + amount + '\'' +
            ", count=" + count +
            ", vol='" + vol + '\'' +
            '}';
  }
}
