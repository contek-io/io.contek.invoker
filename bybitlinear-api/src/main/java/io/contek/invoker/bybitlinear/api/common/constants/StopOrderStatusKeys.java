package io.contek.invoker.bybitlinear.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class StopOrderStatusKeys {

  public static final String _Active = "Active";

  public static final String _Untriggered = "Untriggered";

  public static final String _Triggered = "Triggered";

  public static final String _Cancelled = "Cancelled";

  public static final String _Rejected = "Rejected";

  public static final String _Deactivated = "Deactivated";

  private StopOrderStatusKeys() {}
}
