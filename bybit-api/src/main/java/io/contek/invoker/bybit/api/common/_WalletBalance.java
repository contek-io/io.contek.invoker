package io.contek.invoker.bybit.api.common;

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

  @Override
  public String toString() {
    return "_WalletBalance{" +
            "equity=" + equity +
            ", available_balance=" + available_balance +
            ", used_margin=" + used_margin +
            ", order_margin=" + order_margin +
            ", position_margin=" + position_margin +
            ", occ_closing_fee=" + occ_closing_fee +
            ", occ_funding_fee=" + occ_funding_fee +
            ", wallet_balance=" + wallet_balance +
            ", realised_pnl=" + realised_pnl +
            ", unrealised_pnl=" + unrealised_pnl +
            ", cum_realised_pnl=" + cum_realised_pnl +
            ", given_cash=" + given_cash +
            ", service_cash=" + service_cash +
            '}';
  }
}
