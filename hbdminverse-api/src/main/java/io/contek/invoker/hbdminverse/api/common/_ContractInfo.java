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

  @Override
  public String toString() {
    return "_ContractInfo{" +
            "symbol='" + symbol + '\'' +
            ", contract_code='" + contract_code + '\'' +
            ", contract_size=" + contract_size +
            ", price_tick=" + price_tick +
            ", delivery_time='" + delivery_time + '\'' +
            ", create_date='" + create_date + '\'' +
            ", contract_status=" + contract_status +
            ", settlement_date='" + settlement_date + '\'' +
            '}';
  }
}
