package io.contek.invoker.bybit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TimeInForces {

  public static final String GoodTillCancel = "GoodTillCancel";

  public static final String ImmediateOrCancel = "ImmediateOrCancel";

  public static final String FillOrKill = "FillOrKill";

  public static final String PostOnly = "PostOnly";

  private TimeInForces() {
  }
}
