package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatusKeys {

  public static final String _New = "New";

  public static final String _PartiallyFilled = "PartiallyFilled";

  public static final String _Filled = "Filled";

  public static final String _Canceled = "Canceled";

  public static final String _Rejected = "Rejected";

  private OrderStatusKeys() {}
}
