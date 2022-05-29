package io.contek.invoker.bitmex.api.common;

public class _Order {

  public String orderID;
  public String clOrdID;
  public Long account;
  public String symbol;
  public String side;
  public Double orderQty;
  public Double price;
  public String currency;
  public String settlCurrency;
  public String ordType;
  public String timeInForce;
  public String execInst;
  public String ordStatus;
  public Double leavesQty;
  public Double cumQty;
  public Double avgPx;
  public String transactTime;
  public String timestamp;
  public String error;

  @Override
  public String toString() {
    return "_Order{" +
            "orderID='" + orderID + '\'' +
            ", clOrdID='" + clOrdID + '\'' +
            ", account=" + account +
            ", symbol='" + symbol + '\'' +
            ", side='" + side + '\'' +
            ", orderQty=" + orderQty +
            ", price=" + price +
            ", currency='" + currency + '\'' +
            ", settlCurrency='" + settlCurrency + '\'' +
            ", ordType='" + ordType + '\'' +
            ", timeInForce='" + timeInForce + '\'' +
            ", execInst='" + execInst + '\'' +
            ", ordStatus='" + ordStatus + '\'' +
            ", leavesQty=" + leavesQty +
            ", cumQty=" + cumQty +
            ", avgPx=" + avgPx +
            ", transactTime='" + transactTime + '\'' +
            ", timestamp='" + timestamp + '\'' +
            ", error='" + error + '\'' +
            '}';
  }
}
