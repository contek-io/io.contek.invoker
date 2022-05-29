package io.contek.invoker.binancedelivery.api.common;

public class _Trade {

  public Long id;
  public Double price;
  public Double qty;
  public Double baseQty;
  public Long time;
  public Boolean isBuyerMaker;

  @Override
  public String toString() {
    return "_Trade{" +
            "id=" + id +
            ", price=" + price +
            ", qty=" + qty +
            ", baseQty=" + baseQty +
            ", time=" + time +
            ", isBuyerMaker=" + isBuyerMaker +
            '}';
  }
}
