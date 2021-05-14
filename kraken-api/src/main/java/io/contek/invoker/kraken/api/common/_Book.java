package io.contek.invoker.kraken.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public final class _Book {

  // snapshot payload
  public List<_BookLevel> as;
  public List<_BookLevel> bs;

  // update payload
  public List<_BookLevel> a;
  public List<_BookLevel> b;
}
