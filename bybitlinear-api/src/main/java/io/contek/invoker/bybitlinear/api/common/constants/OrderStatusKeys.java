package io.contek.invoker.bybitlinear.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatusKeys {

  public static final String _Created = "Created";

  public static final String _Rejected = "Rejected";

  public static final String _New = "New";

  public static final String _PartiallyFilled = "PartiallyFilled";

  public static final String _Filled = "Filled";

  public static final String _Cancelled = "Cancelled";

  public static final String _PendingCancel = "PendingCancel";

  private OrderStatusKeys() {}
}
