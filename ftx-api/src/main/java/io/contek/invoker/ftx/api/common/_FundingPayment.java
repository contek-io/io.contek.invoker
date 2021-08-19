package io.contek.invoker.ftx.api.common;

public class _FundingPayment {

    public String future;
    public Long id;
    public Double payment;
    public String time;
    public Double rate;

    @Override
    public String toString() {
        return "_FundingPayment{" +
                "future='" + future + '\'' +
                ", id=" + id +
                ", payment=" + payment +
                ", time='" + time + '\'' +
                ", rate=" + rate +
                '}';
    }
}
