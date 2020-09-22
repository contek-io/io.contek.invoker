package io.contek.invoker.bitmex.api.common.constants;

import javax.annotation.concurrent.Immutable;

@Immutable
public final class InstrumentTypes {

  // Futures
  public static final String FFCCSX = "FFCCSX";
  // Perpetual contracts
  public static final String FFWCSX = "FFWCSX";
  // UPs
  public static final String OCECCS = "OCECCS";
  // DOWNs
  public static final String OPECCS = "OPECCS";
  // Indices
  public static final String MRCXXX = "MRCXXX";
  // Deprecated
  public static final String FMXXS = "FMXXS";
  // Deprecated
  public static final String FXXXS = "FXXXS";

  private InstrumentTypes() {}
}
