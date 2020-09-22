package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderTypes {

  public static final String Market = "Market";

  public static final String Limit = "Limit";

  public static final String Stop = "Stop";

  public static final String StopLimit = "StopLimit";

  private OrderTypes() {}
}
