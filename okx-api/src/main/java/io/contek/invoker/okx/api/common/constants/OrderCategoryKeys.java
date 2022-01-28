package io.contek.invoker.okx.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderCategoryKeys {

  public static final String _twap = "twap";

  public static final String _adl = "adl";

  public static final String _full_liquidation = "full_liquidation";

  public static final String _partial_liquidation = "partial_liquidation";

  public static final String _delivery = "delivery";

  public static final String _ddh = "ddh";

  private OrderCategoryKeys() {}
}
