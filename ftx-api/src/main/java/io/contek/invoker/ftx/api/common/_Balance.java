package io.contek.invoker.ftx.api.common;

public class _Balance {
    public String currency;
    public int total;
    public int locked;
    public int free;
    public int unsettledProceeds;
    public int unsettledCosts;
    public int overall;

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
