package io.contek.invoker.deribit.api.common;

import java.util.List;

public class _PlaceOrderResponse {
  public _Order order;
  public List<_Trade> trades;

  @Override
  public String toString() {
    return "_PlaceOrderResponse{" + "order=" + order + ", trades=" + trades + '}';
  }
}
