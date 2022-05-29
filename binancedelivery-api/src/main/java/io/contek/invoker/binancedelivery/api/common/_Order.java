package io.contek.invoker.binancedelivery.api.common;

public class _Order {

  public Double avgPrice;
  public String clientOrderId;
  public Double cumQty;
  public Double cumBase;
  public Double executedQty;
  public Long orderId;
  public Double origQty;
  public String origType;
  public Double price;
  public Boolean reduceOnly;
  public String side;
  public String positionSide;
  public String status;
  public Double stopPrice;
  public Boolean closePosition;
  public String symbol;
  public String pair;
  public Long time;
  public String timeInForce;
  public String type;
  public Double activatePrice;
  public Double priceRate;
  public Long updateTime;
  public String workingType;
  public Boolean priceProtect;

  @Override
  public String toString() {
    return "_Order{" +
            "avgPrice=" + avgPrice +
            ", clientOrderId='" + clientOrderId + '\'' +
            ", cumQty=" + cumQty +
            ", cumBase=" + cumBase +
            ", executedQty=" + executedQty +
            ", orderId=" + orderId +
            ", origQty=" + origQty +
            ", origType='" + origType + '\'' +
            ", price=" + price +
            ", reduceOnly=" + reduceOnly +
            ", side='" + side + '\'' +
            ", positionSide='" + positionSide + '\'' +
            ", status='" + status + '\'' +
            ", stopPrice=" + stopPrice +
            ", closePosition=" + closePosition +
            ", symbol='" + symbol + '\'' +
            ", pair='" + pair + '\'' +
            ", time=" + time +
            ", timeInForce='" + timeInForce + '\'' +
            ", type='" + type + '\'' +
            ", activatePrice=" + activatePrice +
            ", priceRate=" + priceRate +
            ", updateTime=" + updateTime +
            ", workingType='" + workingType + '\'' +
            ", priceProtect=" + priceProtect +
            '}';
  }
}
