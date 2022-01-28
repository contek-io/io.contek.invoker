package io.contek.invoker.okx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _AccountInformation {

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
  public String username;
  public List<_Position> positions;
}
