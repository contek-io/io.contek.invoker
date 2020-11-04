package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class InstrumentTypeKeys {

  // Futures
  public static final String _FFCCSX = "FFCCSX";
  // Perpetual contracts
  public static final String _FFWCSX = "FFWCSX";
  // UPs
  public static final String _OCECCS = "OCECCS";
  // DOWNs
  public static final String _OPECCS = "OPECCS";
  // Indices
  public static final String _MRCXXX = "MRCXXX";
  // Deprecated
  public static final String _FMXXS = "FMXXS";
  // Deprecated
  public static final String _FXXXS = "FXXXS";

  private InstrumentTypeKeys() {}
}
