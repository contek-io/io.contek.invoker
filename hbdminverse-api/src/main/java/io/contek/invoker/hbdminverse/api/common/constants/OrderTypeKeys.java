package io.contek.invoker.hbdminverse.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderTypeKeys {

  public static final int _1_quotation = 1;
  public static final int _2_cancelled_order = 2;
  public static final int _3_forced_liquidation = 3;
  public static final int _4_delivery_order = 4;

  private OrderTypeKeys() {}
}
