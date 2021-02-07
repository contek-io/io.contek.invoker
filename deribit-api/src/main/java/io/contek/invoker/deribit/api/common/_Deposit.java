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
}
