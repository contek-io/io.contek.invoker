package io.contek.invoker.bybit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatusKeys {

  public static final String _Created = "Created";

  public static final String _Rejected = "Rejected";

  public static final String _New = "New";

  public static final String _PartiallyFilled = "PartiallyFilled";

  public static final String _PartiallyFilledCanceled = "PartiallyFilledCanceled";

  public static final String _Filled = "Filled";

  public static final String _Cancelled = "Cancelled";

  public static final String _Untriggered = "Untriggered";

  public static final String _Triggered = "Triggered";

  public static final String _Deactivated = "Deactivated";

  public static final String _Active = "Active";

  private OrderStatusKeys() {}
}
