package io.contek.invoker.bybitlinear.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _WalletBalance {

  public double equity;
  public double available_balance;
  public double used_margin;
  public double order_margin;
  public double position_margin;
  public double occ_closing_fee;
  public double occ_funding_fee;
  public double wallet_balance;
  public double realised_pnl;
  public double unrealised_pnl;
  public double cum_realised_pnl;
  public double given_cash;
  public double service_cash;
}
