package io.contek.invoker.bitmex.api.common.constants;

public final class TickDirectionKeys {

  /** this trade's price is lower than the previous one. */
  public static final String _MinusTick = "MinusTick";

  /**
   * this trade's price is equal to previous one, but lower than the last trade of
   * io.contek.invoker.ftx.api.a different price.
   */
  public static final String _ZeroMinusTick = "ZeroMinusTick";

  /**
   * this trade's price is equal to previous one, but higher than the last trade of
   * io.contek.invoker.ftx.api.a different price.
   */
  public static final String _ZeroPlusTick = "ZeroPlusTick";

  /** this trade's price is higher than the previous one. */
  public static final String _PlusTick = "PlusTick";

  private TickDirectionKeys() {}
}
