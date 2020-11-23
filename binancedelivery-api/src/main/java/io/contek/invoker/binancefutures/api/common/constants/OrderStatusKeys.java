package io.contek.invoker.binancefutures.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatusKeys {

  public static final String _NEW = "NEW";

  public static final String _PARTIALLY_FILLED = "PARTIALLY_FILLED";

  public static final String _FILLED = "FILLED";

  public static final String _CANCELED = "CANCELED";

  public static final String _PENDING_CANCEL = "PENDING_CANCEL";

  public static final String _REJECTED = "REJECTED";

  public static final String _EXPIRED = "EXPIRED";

  private OrderStatusKeys() {
  }
}
