package io.contek.invoker.bybit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatuses {

  public static final String Created = "Created";

  public static final String Rejected = "Rejected";

  public static final String New = "New";

  public static final String PartiallyFilled = "PartiallyFilled";

  public static final String Filled = "Filled";

  public static final String Cancelled = "Cancelled";

  public static final String PendingCancel = "PendingCancel";

  private OrderStatuses() {
  }
}
