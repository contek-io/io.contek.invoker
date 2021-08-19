package io.contek.invoker.bybit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _WalletFundRecord {

  public Long id;
  public Long user_id;
  public String coin;
  public Long wallet_id;
  public String type;
  public String amount;
  public String tx_id;
  public String address;
  public String wallet_balance;
  public String exec_time;
  public Long cross_seq;

  @Override
  public String toString() {
    return "_WalletFundRecord{" +
            "id=" + id +
            ", user_id=" + user_id +
            ", coin='" + coin + '\'' +
            ", wallet_id=" + wallet_id +
            ", type='" + type + '\'' +
            ", amount='" + amount + '\'' +
            ", tx_id='" + tx_id + '\'' +
            ", address='" + address + '\'' +
            ", wallet_balance='" + wallet_balance + '\'' +
            ", exec_time='" + exec_time + '\'' +
            ", cross_seq=" + cross_seq +
            '}';
  }
}
