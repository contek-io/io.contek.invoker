package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class OrderExecutionInstructionKeys {

  public static final String _ParticipateDoNotInitiate = "ParticipateDoNotInitiate";

  public static final String _AllOrNone = "AllOrNone";

  public static final String _MarkPrice = "MarkPrice";

  public static final String _LastPrice = "LastPrice";

  public static final String _IndexPrice = "IndexPrice";

  public static final String _ReduceOnly = "ReduceOnly";

  public static final String _Close = "Close";

  private OrderExecutionInstructionKeys() {}
}
