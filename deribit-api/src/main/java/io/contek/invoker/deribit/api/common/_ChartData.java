package io.contek.invoker.deribit.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _ChartData {

  public List<Double> close;
  public List<Double> cost;
  public List<Double> high;
  public List<Double> low;
  public List<Double> open;
  public String status;
  public List<Long> ticks;
  public List<Double> volume;
}
