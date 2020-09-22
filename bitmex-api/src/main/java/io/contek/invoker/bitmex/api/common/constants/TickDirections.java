package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class TickDirections {

  /** this trade's price is lower than the previous one. */
  public static final String MinusTick = "MinusTick";

  /**
   * this trade's price is equal to previous one, but lower than the last trade of a different
   * price.
   */
  public static final String ZeroMinusTick = "ZeroMinusTick";

  /**
   * this trade's price is equal to previous one, but higher than the last trade of a different
   * price.
   */
  public static final String ZeroPlusTick = "ZeroPlusTick";

  /** this trade's price is higher than the previous one. */
  public static final String PlusTick = "PlusTick";

  private TickDirections() {}
}
