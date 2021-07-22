package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

@NotThreadSafe
public class _Order implements Serializable {

  public String createdAt;
  public Double filledSize;
  public String future;
  public Long id;
  public String market;
  public Double price;
  public Double avgFillPrice;
  public Double remainingSize;
  public String side;
  public Double size;
  public String status;
  public String type;
  public Boolean reduceOnly;
  public Boolean ioc;
  public Boolean postOnly;
  public String clientId;

  @Override
  public String toString() {
    return "_Order{"
        + "createdAt='"
        + createdAt
        + '\''
        + ", filledSize="
        + filledSize
        + ", future='"
        + future
        + '\''
        + ", id="
        + id
        + ", market='"
        + market
        + '\''
        + ", price="
        + price
        + ", avgFillPrice="
        + avgFillPrice
        + ", remainingSize="
        + remainingSize
        + ", side='"
        + side
        + '\''
        + ", size="
        + size
        + ", status='"
        + status
        + '\''
        + ", type='"
        + type
        + '\''
        + ", reduceOnly="
        + reduceOnly
        + ", ioc="
        + ioc
        + ", postOnly="
        + postOnly
        + ", clientId='"
        + clientId
        + '\''
        + '}';
  }
}
