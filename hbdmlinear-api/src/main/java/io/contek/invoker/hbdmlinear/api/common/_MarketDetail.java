package io.contek.invoker.hbdmlinear.api.common;

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
  public String trade_turnover;
}
