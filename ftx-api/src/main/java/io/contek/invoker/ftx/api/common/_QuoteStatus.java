package io.contek.invoker.ftx.api.common;

public class _QuoteStatus {
  public String baseCoin;
  public Double cost;
  public Boolean expired;
  public Boolean filled;
  public String fromCoin;
  public Long id;
  public Double price;
  public Double proceeds;
  public String quoteCoin;
  public String side;
  public String toCoin;

  @Override
  public String toString() {
    return "_QuoteStatus{"
        + "baseCoin='"
        + baseCoin
        + '\''
        + ", cost="
        + cost
        + ", expired="
        + expired
        + ", filled="
        + filled
        + ", fromCoin='"
        + fromCoin
        + '\''
        + ", id="
        + id
        + ", price="
        + price
        + ", proceeds="
        + proceeds
        + ", quoteCoin='"
        + quoteCoin
        + '\''
        + ", side='"
        + side
        + '\''
        + ", toCoin='"
        + toCoin
        + '\''
        + '}';
  }
}
