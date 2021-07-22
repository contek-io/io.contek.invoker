package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Deposit {

  public String address;
  public long amount;
  public String currency;
  public long received_timestamp;
  public String state;
  public String transaction_id;
  public long updated_timestamp;

  @Override
  public String toString() {
    return "_Deposit{"
        + "address='"
        + address
        + '\''
        + ", amount="
        + amount
        + ", currency='"
        + currency
        + '\''
        + ", received_timestamp="
        + received_timestamp
        + ", state='"
        + state
        + '\''
        + ", transaction_id='"
        + transaction_id
        + '\''
        + ", updated_timestamp="
        + updated_timestamp
        + '}';
  }
}
