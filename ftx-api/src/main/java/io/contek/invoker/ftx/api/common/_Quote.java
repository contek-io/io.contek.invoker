package io.contek.invoker.ftx.api.common;

public class _Quote {

  public Integer id;
  public String baseCurrency;
  public String quoteCurrency;
  public String side;
  public Double baseCurrencySize;
  public Double quoteCurrencySize;
  public Double price;
  public String requestedAt;
  public String quotedAt;
  public String expiry;
  public Boolean filled;
  public Integer orderId;
  public Boolean counterpartyAutoSettles;
  public Boolean settledImmediately;
  public String settlementTime;
  public Double deferCostRate;
  public Double deferProceedsRate;
  public Integer settlementPriority;
  public String costCurrency;
  public Double cost;
  public String proceedsCurrency;
  public Double proceeds;
  public Integer totalDeferCostPaid;
  public Integer totalDeferProceedsPaid;
  public Double unsettledCost;
  public Integer unsettledProceeds;
  public String userFullySettledAt;
  public String counterpartyFullySettledAt;

  @Override
  public String toString() {
    return "_Quote{"
        + "id="
        + id
        + ", baseCurrency='"
        + baseCurrency
        + '\''
        + ", quoteCurrency='"
        + quoteCurrency
        + '\''
        + ", side='"
        + side
        + '\''
        + ", baseCurrencySize="
        + baseCurrencySize
        + ", quoteCurrencySize="
        + quoteCurrencySize
        + ", price="
        + price
        + ", requestedAt='"
        + requestedAt
        + '\''
        + ", quotedAt='"
        + quotedAt
        + '\''
        + ", expiry='"
        + expiry
        + '\''
        + ", filled="
        + filled
        + ", orderId="
        + orderId
        + ", counterpartyAutoSettles="
        + counterpartyAutoSettles
        + ", settledImmediately="
        + settledImmediately
        + ", settlementTime='"
        + settlementTime
        + '\''
        + ", deferCostRate="
        + deferCostRate
        + ", deferProceedsRate="
        + deferProceedsRate
        + ", settlementPriority="
        + settlementPriority
        + ", costCurrency='"
        + costCurrency
        + '\''
        + ", cost="
        + cost
        + ", proceedsCurrency='"
        + proceedsCurrency
        + '\''
        + ", proceeds="
        + proceeds
        + ", totalDeferCostPaid="
        + totalDeferCostPaid
        + ", totalDeferProceedsPaid="
        + totalDeferProceedsPaid
        + ", unsettledCost="
        + unsettledCost
        + ", unsettledProceeds="
        + unsettledProceeds
        + ", userFullySettledAt='"
        + userFullySettledAt
        + '\''
        + ", counterpartyFullySettledAt='"
        + counterpartyFullySettledAt
        + '\''
        + '}';
  }
}
