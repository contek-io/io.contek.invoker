package io.contek.invoker.ftx.api.common;

public class _Settlement {

    public int id;
    public String currency;
    public double amount;
    public String time;
    public int quoteId;

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
