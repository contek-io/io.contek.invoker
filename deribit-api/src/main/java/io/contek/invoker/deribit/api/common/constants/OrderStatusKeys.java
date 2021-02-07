package io.contek.invoker.deribit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatusKeys {


  public static final String _open = "open";

  public static final String filled = "filled";

  public static final String rejected = "rejected";

  public static final String cancelled = "cancelled";

  public static final String untriggered = "untriggered";

  private OrderStatusKeys() {
  }
}
