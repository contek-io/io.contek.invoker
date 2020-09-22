package io.contek.invoker.binancefutures.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderTypes {

  public static final String LIMIT = "LIMIT";

  public static final String MARKET = "MARKET";

  public static final String STOP_LOSS = "STOP_LOSS";

  public static final String STOP_LOSS_LIMIT = "STOP_LOSS_LIMIT";

  public static final String TAKE_PROFIT = "TAKE_PROFIT";

  public static final String TAKE_PROFIT_LIMIT = "TAKE_PROFIT_LIMIT";

  public static final String LIMIT_MAKER = "LIMIT_MAKER";

  private OrderTypes() {}
}
