package io.contek.invoker.binanceinverse.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class ContractStatusKeys {

  public static final String _PENDING_TRADING = "PENDING_TRADING";

  public static final String _TRADING = "TRADING";

  public static final String _PRE_DELIVERING = "PRE_DELIVERING";

  public static final String _DELIVERING = "DELIVERING";

  public static final String _DELIVERED = "DELIVERED";

  private ContractStatusKeys() {
  }
}
