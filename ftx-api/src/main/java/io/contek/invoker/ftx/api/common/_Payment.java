package io.contek.invoker.ftx.api.common;

public class _Payment {

    public int id;
    public String currency;
    public double amount;
    public String windowStart;
    public String windowEnd;
    public int quoteId;
    public int deferRate;
    public int notionalTarget;

    @Override
    public String toString() {
        return "_Payment{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", windowStart='" + windowStart + '\'' +
                ", windowEnd='" + windowEnd + '\'' +
                ", quoteId=" + quoteId +
                ", deferRate=" + deferRate +
                ", notionalTarget=" + notionalTarget +
                '}';
    }
}
