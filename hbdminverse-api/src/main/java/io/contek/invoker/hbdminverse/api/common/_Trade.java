package io.contek.invoker.hbdminverse.api.common;

public class _Trade {

  public double amount;
  public String direction;
  public long id;
  public double price;
  public long ts;
  public double quantity;
  public double trade_turnover;

  @Override
  public String toString() {
    return "_Trade{" +
            "amount=" + amount +
            ", direction='" + direction + '\'' +
            ", id=" + id +
            ", price=" + price +
            ", ts=" + ts +
            ", quantity=" + quantity +
            ", trade_turnover=" + trade_turnover +
            '}';
  }
}
