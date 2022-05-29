package io.contek.invoker.binancespot.api.common;

public class _Order {

  public String symbol;
  public Long orderId;
  public Long orderListId;
  public String clientOrderId;
  public String price;
  public String origQty;
  public String executedQty;
  public String cummulativeQuoteQty;
  public String status;
  public String timeInForce;
  public String type;
  public String side;
  public String stopPrice;
  public String icebergQty;
  public Long time;
  public Long updateTime;
  public Boolean isWorking;
  public String origClientOrderId;
  public String origQuoteOrderQty;

  @Override
  public String toString() {
    return "_Order{" +
            "symbol='" + symbol + '\'' +
            ", orderId=" + orderId +
            ", orderListId=" + orderListId +
            ", clientOrderId='" + clientOrderId + '\'' +
            ", price='" + price + '\'' +
            ", origQty='" + origQty + '\'' +
            ", executedQty='" + executedQty + '\'' +
            ", cummulativeQuoteQty='" + cummulativeQuoteQty + '\'' +
            ", status='" + status + '\'' +
            ", timeInForce='" + timeInForce + '\'' +
            ", type='" + type + '\'' +
            ", side='" + side + '\'' +
            ", stopPrice='" + stopPrice + '\'' +
            ", icebergQty='" + icebergQty + '\'' +
            ", time=" + time +
            ", updateTime=" + updateTime +
            ", isWorking=" + isWorking +
            ", origClientOrderId='" + origClientOrderId + '\'' +
            ", origQuoteOrderQty='" + origQuoteOrderQty + '\'' +
            '}';
  }
}
