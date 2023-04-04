package io.contek.invoker.binanceinverse.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderTypeKeys {

  public static final String _LIMIT = "LIMIT";

  public static final String _MARKET = "MARKET";

  public static final String _STOP_LOSS = "STOP_LOSS";

  public static final String _STOP_LOSS_LIMIT = "STOP_LOSS_LIMIT";

  public static final String _TAKE_PROFIT = "TAKE_PROFIT";

  public static final String _TAKE_PROFIT_LIMIT = "TAKE_PROFIT_LIMIT";

  public static final String _LIMIT_MAKER = "LIMIT_MAKER";

  private OrderTypeKeys() {
  }
}
