package io.contek.invoker.binancedelivery.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.util.List;

@NotThreadSafe
public class _Account {

  public Boolean canDeposit;
  public Boolean canTrade;
  public Boolean canWithdraw;
  public Integer feeTier;
  public Long updateTime;
  public List<_AccountAsset> assets;
  public List<_AccountPosition> positions;

  @Override
  public String toString() {
    return "_Account{"
        + "canDeposit="
        + canDeposit
        + ", canTrade="
        + canTrade
        + ", canWithdraw="
        + canWithdraw
        + ", feeTier="
        + feeTier
        + ", updateTime="
        + updateTime
        + ", assets="
        + assets
        + ", positions="
        + positions
        + '}';
  }
}
