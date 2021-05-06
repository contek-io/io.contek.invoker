package io.contek.invoker.hbdminverse.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _ContractInfo {

  public String symbol;
  public String contract_code;
  public double contract_size;
  public double price_tick;
  public String delivery_time;
  public String create_date;
  public int contract_status;
  public String settlement_date;
}
