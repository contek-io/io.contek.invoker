package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _PlaceOrderResponse {

  public _Order order;
  public List<_Trade> trades;
}
