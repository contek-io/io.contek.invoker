package io.contek.invoker.bybitlinear.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TickDirectionKeys {

  // Rise in price
  public static final String _PlusTick = "PlusTick";

  // Keep up with the last trad, the last time with the last is rise in price
  public static final String _ZeroPlusTick = "ZeroPlusTick";

  // Prices are down
  public static final String _MinusTick = "MinusTick";

  // Keep up with the last trad, the last time with the last is Prices are down
  public static final String _ZeroMinusTick = "ZeroMinusTick";

  private TickDirectionKeys() {}
}
