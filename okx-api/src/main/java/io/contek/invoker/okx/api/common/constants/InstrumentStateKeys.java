package io.contek.invoker.okx.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class InstrumentStateKeys {

  public static final String _live = "live";

  public static final String _suspend = "suspend";

  public static final String _preopen = "preopen";

  public static final String _settlement = "settlement";

  private InstrumentStateKeys() {}
}
