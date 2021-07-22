package io.contek.invoker.ftx.api.common;

public class _Payment {

    public Integer id;
    public String currency;
    public Double amount;
    public String windowStart;
    public String windowEnd;
    public Integer quoteId;
    public Integer deferRate;
    public Integer notionalTarget;

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
