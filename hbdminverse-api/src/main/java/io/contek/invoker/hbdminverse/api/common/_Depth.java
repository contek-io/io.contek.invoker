package io.contek.invoker.hbdminverse.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _Depth {

  public List<_DepthLevel> asks;
  public List<_DepthLevel> bids;
  public String ch;
  public long id;
  public long mrid;
  public long ts;
  public long version;
}
