package io.contek.invoker.bybit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TickDirections {

  // Rise in price
  public static final String PlusTick = "PlusTick";

  // Keep up with the last trad, the last time with the last is rise in price
  public static final String ZeroPlusTick = "ZeroPlusTick";

  // Prices are down
  public static final String MinusTick = "MinusTick";

  // Keep up with the last trad, the last time with the last is Prices are down
  public static final String ZeroMinusTick = "ZeroMinusTick";

  private TickDirections() {}
}
