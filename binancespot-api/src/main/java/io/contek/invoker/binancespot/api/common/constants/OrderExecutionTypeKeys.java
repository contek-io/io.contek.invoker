package io.contek.invoker.binancespot.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderExecutionTypeKeys {

  public static final String _NEW = "NEW";

  public static final String _CANCELED = "CANCELED";

  public static final String _REPLACED = "REPLACED";

  public static final String _REJECTED = "REJECTED";

  public static final String _TRADE = "TRADE";

  public static final String _EXPIRED = "EXPIRED";

  private OrderExecutionTypeKeys() {}
}
