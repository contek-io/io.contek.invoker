package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _FundingPayments implements Serializable {

    public String future;
    public int id;
    public double payment;
    public String time;

    @Override
    public String toString() {
        return "_FundingPayments{" +
                "future='" + future + '\'' +
                ", id=" + id +
                ", payment=" + payment +
                ", time='" + time + '\'' +
                '}';
    }
}
