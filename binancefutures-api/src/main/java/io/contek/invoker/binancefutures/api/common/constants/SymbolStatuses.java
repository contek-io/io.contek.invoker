package io.contek.invoker.binancefutures.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class SymbolStatuses {
  public static final String PRE_TRADING = "PRE_TRADING";
  public static final String TRADING = "TRADING";
  public static final String POST_TRADING = "POST_TRADING";
  public static final String END_OF_DAY = "END_OF_DAY";
  public static final String HALT = "HALT";
  public static final String AUCTION_MATCH = "AUCTION_MATCH";
  public static final String BREAK = "BREAK";

  private SymbolStatuses() {}
}
