package io.contek.invoker.deribit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderTriggerTypeKeys {

  public static final String _stop = "stop";

  public static final String _trailingStop = "trailingStop";

  public static final String _takeProfit = "takeProfit";

  private OrderTriggerTypeKeys() {}
}
