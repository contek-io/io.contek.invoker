package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _Currency {

  public String coin_type;
  public String currency;
  public String currency_long;
  public Integer fee_precision;
  public Integer min_confirmations;
  public Double min_withdrawal_fee;
  public Double withdrawal_fee;
  public List<_WithdrawPriority> withdrawal_priorities;

  @Override
  public String toString() {
    return "_Currency{" +
            "coin_type='" + coin_type + '\'' +
            ", currency='" + currency + '\'' +
            ", currency_long='" + currency_long + '\'' +
            ", fee_precision=" + fee_precision +
            ", min_confirmations=" + min_confirmations +
            ", min_withdrawal_fee=" + min_withdrawal_fee +
            ", withdrawal_fee=" + withdrawal_fee +
            ", withdrawal_priorities=" + withdrawal_priorities +
            '}';
  }
}
