package io.contek.invoker.ftx.api.common;

public class _Quote {

    public int id;
    public String baseCurrency;
    public String quoteCurrency;
    public String side;
    public double baseCurrencySize;
    public double quoteCurrencySize;
    public double price;
    public String requestedAt;
    public String quotedAt;
    public String expiry;
    public boolean filled;
    public int orderId;
    public boolean counterpartyAutoSettles;
    public boolean settledImmediately;
    public String settlementTime;
    public double deferCostRate;
    public double deferProceedsRate;
    public int settlementPriority;
    public String costCurrency;
    public double cost;
    public String proceedsCurrency;
    public double proceeds;
    public int totalDeferCostPaid;
    public int totalDeferProceedsPaid;
    public double unsettledCost;
    public int unsettledProceeds;
    public String userFullySettledAt;
    public String counterpartyFullySettledAt;

    @Override
    public String toString() {
        return "_Quote{" +
                "id=" + id +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", quoteCurrency='" + quoteCurrency + '\'' +
                ", side='" + side + '\'' +
                ", baseCurrencySize=" + baseCurrencySize +
                ", quoteCurrencySize=" + quoteCurrencySize +
                ", price=" + price +
                ", requestedAt='" + requestedAt + '\'' +
                ", quotedAt='" + quotedAt + '\'' +
                ", expiry='" + expiry + '\'' +
                ", filled=" + filled +
                ", orderId=" + orderId +
                ", counterpartyAutoSettles=" + counterpartyAutoSettles +
                ", settledImmediately=" + settledImmediately +
                ", settlementTime='" + settlementTime + '\'' +
                ", deferCostRate=" + deferCostRate +
                ", deferProceedsRate=" + deferProceedsRate +
                ", settlementPriority=" + settlementPriority +
                ", costCurrency='" + costCurrency + '\'' +
                ", cost=" + cost +
                ", proceedsCurrency='" + proceedsCurrency + '\'' +
                ", proceeds=" + proceeds +
                ", totalDeferCostPaid=" + totalDeferCostPaid +
                ", totalDeferProceedsPaid=" + totalDeferProceedsPaid +
                ", unsettledCost=" + unsettledCost +
                ", unsettledProceeds=" + unsettledProceeds +
                ", userFullySettledAt='" + userFullySettledAt + '\'' +
                ", counterpartyFullySettledAt='" + counterpartyFullySettledAt + '\'' +
                '}';
    }
}
