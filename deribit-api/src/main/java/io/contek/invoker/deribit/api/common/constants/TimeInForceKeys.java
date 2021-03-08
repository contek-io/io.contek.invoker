package io.contek.invoker.deribit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TimeInForceKeys {

  public static String _good_til_cancelled = "good_til_cancelled";

  public static String _fill_or_kill = "fill_or_kill";

  public static String _immediate_or_cancel = "immediate_or_cancel";

  private TimeInForceKeys() {}
}
