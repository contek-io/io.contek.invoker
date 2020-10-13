package io.contek.invoker.bybit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public class TriggerPriceTypes {

  public static final String LastPrice = "LastPrice";

  public static final String IndexPrice = "IndexPrice";

  public static final String MarkPrice = "MarkPrice";

  private TriggerPriceTypes() {
  }
}
