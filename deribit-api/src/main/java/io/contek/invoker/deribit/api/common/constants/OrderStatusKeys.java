package io.contek.invoker.deribit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatusKeys {

  public static final String _open = "open";

  public static final String _filled = "filled";

  public static final String _rejected = "rejected";

  public static final String _cancelled = "cancelled";

  public static final String _untriggered = "untriggered";

  private OrderStatusKeys() {}
}
