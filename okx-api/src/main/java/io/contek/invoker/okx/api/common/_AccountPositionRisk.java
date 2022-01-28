package io.contek.invoker.okx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _AccountPositionRisk {

  public String adjEq;
  public List<_BalanceRisk> balData;
  public List<_PositionRisk> posData;
  public String ts;
}
