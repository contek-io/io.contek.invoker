package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TimeInForces {

  public static final String Day = "Day";

  public static final String GoodTillCancel = "GoodTillCancel";

  public static final String ImmediateOrCancel = "ImmediateOrCancel";

  public static final String FillOrKill = "FillOrKill";

  private TimeInForces() {}
}
