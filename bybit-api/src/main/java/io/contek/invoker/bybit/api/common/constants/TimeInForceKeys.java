package io.contek.invoker.bybit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TimeInForceKeys {

  public static final String _GTC = "GTC";

  public static final String _IOC = "IOC";

  public static final String _FOK = "FOK";

  public static final String _PostOnly = "PostOnly";

  private TimeInForceKeys() {}
}
