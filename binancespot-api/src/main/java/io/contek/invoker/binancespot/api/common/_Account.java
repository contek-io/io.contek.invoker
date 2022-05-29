package io.contek.invoker.binancespot.api.common;

import java.util.List;

public class _Account {

  public Integer makerCommission;
  public Integer takerCommission;
  public Integer buyerCommission;
  public Integer sellerCommission;
  public Boolean canTrade;
  public Boolean canWithdraw;
  public Boolean canDeposit;
  public Long updateTime;
  public String accountType;
  public List<_AccountBalance> balances;
  public List<String> permissions;

  @Override
  public String toString() {
    return "_Account{" +
            "makerCommission=" + makerCommission +
            ", takerCommission=" + takerCommission +
            ", buyerCommission=" + buyerCommission +
            ", sellerCommission=" + sellerCommission +
            ", canTrade=" + canTrade +
            ", canWithdraw=" + canWithdraw +
            ", canDeposit=" + canDeposit +
            ", updateTime=" + updateTime +
            ", accountType='" + accountType + '\'' +
            ", balances=" + balances +
            ", permissions=" + permissions +
            '}';
  }
}
