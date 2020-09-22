package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class InstrumentStates {

  public static final String Open = "Open";

  public static final String Unlisted = "Unlisted";

  public static final String Settled = "Settled";

  private InstrumentStates() {}
}
