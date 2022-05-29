package io.contek.invoker.bybit.api.common;

public class _OrderBookLevel {

  public String symbol;
  public double price;
  public int size;
  public String side;

  @Override
  public String toString() {
    return "_OrderBookLevel{" +
            "symbol='" + symbol + '\'' +
            ", price=" + price +
            ", size=" + size +
            ", side='" + side + '\'' +
            '}';
  }
}
