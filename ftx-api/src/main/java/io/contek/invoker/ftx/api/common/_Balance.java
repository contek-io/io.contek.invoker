package io.contek.invoker.ftx.api.common;

public class _Balance {
    public String currency;
    public Integer total;
    public Integer locked;
    public Integer free;
    public Integer unsettledProceeds;
    public Integer unsettledCosts;
    public Integer overall;

    @Override
    public String toString() {
        return "_Balance{" +
                "currency='" + currency + '\'' +
                ", total=" + total +
                ", locked=" + locked +
                ", free=" + free +
                ", unsettledProceeds=" + unsettledProceeds +
                ", unsettledCosts=" + unsettledCosts +
                ", overall=" + overall +
                '}';
    }
}
