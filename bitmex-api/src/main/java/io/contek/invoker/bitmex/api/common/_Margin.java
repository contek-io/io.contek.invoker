package io.contek.invoker.bitmex.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Margin {

  public Long account;
  public String currency;
  public Long BitmexSatoshi;
  public Long initMargin;
  public Long maintMargin;
  public Long realisedPnl;
  public Long unrealisedPnl;
  public Long walletBalance;
  public Long marginBalance;
  public Double marginLeverage;
  public Long availableMargin;
  public Long withdrawableMargin;
  public String timestamp;

  @Override
  public String toString() {
    return "_Margin{" +
            "account=" + account +
            ", currency='" + currency + '\'' +
            ", BitmexSatoshi=" + BitmexSatoshi +
            ", initMargin=" + initMargin +
            ", maintMargin=" + maintMargin +
            ", realisedPnl=" + realisedPnl +
            ", unrealisedPnl=" + unrealisedPnl +
            ", walletBalance=" + walletBalance +
            ", marginBalance=" + marginBalance +
            ", marginLeverage=" + marginLeverage +
            ", availableMargin=" + availableMargin +
            ", withdrawableMargin=" + withdrawableMargin +
            ", timestamp='" + timestamp + '\'' +
            '}';
  }
}
