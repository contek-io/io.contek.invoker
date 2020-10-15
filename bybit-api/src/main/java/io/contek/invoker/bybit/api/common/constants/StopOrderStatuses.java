package io.contek.invoker.bybit.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class StopOrderStatuses {

  public static final String Active = "Active";

  public static final String Untriggered = "Untriggered";

  public static final String Triggered = "Triggered";

  public static final String Cancelled = "Cancelled";

  public static final String Rejected = "Rejected";

  public static final String Deactivated = "Deactivated";

  private StopOrderStatuses() {
  }
}
