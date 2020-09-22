package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderExecutionInstructions {

  public static final String ParticipateDoNotInitiate = "ParticipateDoNotInitiate";

  public static final String AllOrNone = "AllOrNone";

  public static final String MarkPrice = "MarkPrice";

  public static final String LastPrice = "LastPrice";

  public static final String IndexPrice = "IndexPrice";

  public static final String ReduceOnly = "ReduceOnly";

  public static final String Close = "Close";

  private OrderExecutionInstructions() {}
}
