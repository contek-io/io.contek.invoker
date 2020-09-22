package io.contek.invoker.binancefutures.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderExecutionTypes {

  public static final String NEW = "NEW";

  public static final String CANCELED = "CANCELED";

  public static final String REPLACED = "REPLACED";

  public static final String REJECTED = "REJECTED";

  public static final String TRADE = "TRADE";

  public static final String EXPIRED = "EXPIRED";

  private OrderExecutionTypes() {}
}
