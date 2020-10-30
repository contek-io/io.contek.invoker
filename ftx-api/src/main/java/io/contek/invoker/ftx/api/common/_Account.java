package io.contek.invoker.ftx.api.common;

import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Account {

  public Boolean backstopProvider;
  public Double collateral;
  public Double freeCollateral;
  public Double initialMarginRequirement;
  public Double leverage;
  public Boolean liquidating;
  public Double maintenanceMarginRequirement;
  public Double makerFee;
  public Double marginFraction;
  public Double openMarginFraction;
  public Double takerFee;
  public Double totalAccountValue;
  public Double totalPositionSize;
  public Double username;
  public List<_Position> positions;
}
