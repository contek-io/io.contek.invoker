package io.contek.invoker.okx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _Balance {

  public String adjEq;
  public String imr;
  public String isoEq;
  public String mgnRatio;
  public String mmr;
  public String notionalUsd;
  public String ordFroz;
  public String totalEq;
  public String uTime;
  public List<_CurrencyBalance> details;
}
