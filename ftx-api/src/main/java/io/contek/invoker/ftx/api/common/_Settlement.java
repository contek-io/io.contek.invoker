package io.contek.invoker.ftx.api.common;

public class _Settlement {

    public Integer id;
    public String currency;
    public Double amount;
    public String time;
    public Integer quoteId;

    @Override
    public String toString() {
        return "_Settlement{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                ", quoteId=" + quoteId +
                '}';
    }
}
