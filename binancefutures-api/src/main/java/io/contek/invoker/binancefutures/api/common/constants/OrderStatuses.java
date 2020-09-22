package io.contek.invoker.binancefutures.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderStatuses {

  public static final String NEW = "NEW";

  public static final String PARTIALLY_FILLED = "PARTIALLY_FILLED";

  public static final String FILLED = "FILLED";

  public static final String CANCELED = "CANCELED";

  public static final String PENDING_CANCEL = "PENDING_CANCEL";

  public static final String REJECTED = "REJECTED";

  public static final String EXPIRED = "EXPIRED";

  private OrderStatuses() {}
}
