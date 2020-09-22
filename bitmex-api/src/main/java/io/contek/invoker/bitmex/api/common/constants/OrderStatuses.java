package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatuses {

  public static final String New = "New";

  public static final String PartiallyFilled = "PartiallyFilled";

  public static final String Filled = "Filled";

  public static final String Canceled = "Canceled";

  public static final String Rejected = "Rejected";

  private OrderStatuses() {}
}
