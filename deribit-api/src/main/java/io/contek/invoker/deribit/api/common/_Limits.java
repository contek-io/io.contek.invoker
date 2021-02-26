package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class _Limits {

  public _Limit non_matching_engine;
  public _Limit matching_engine;
  public _Limit futures;
  public _Limit options;
  public _Limit perpetuals;
}
