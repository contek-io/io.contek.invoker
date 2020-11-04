package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TimeInForceKeys {

  public static final String _Day = "Day";

  public static final String _GoodTillCancel = "GoodTillCancel";

  public static final String _ImmediateOrCancel = "ImmediateOrCancel";

  public static final String _FillOrKill = "FillOrKill";

  private TimeInForceKeys() {}
}
