package io.contek.invoker.binancefutures.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class SymbolStatusKeys {

  public static final String _PRE_TRADING = "PRE_TRADING";

  public static final String _TRADING = "TRADING";

  public static final String _POST_TRADING = "POST_TRADING";

  public static final String _END_OF_DAY = "END_OF_DAY";

  public static final String _HALT = "HALT";

  public static final String _AUCTION_MATCH = "AUCTION_MATCH";

  public static final String _BREAK = "BREAK";

  private SymbolStatusKeys() {}
}
