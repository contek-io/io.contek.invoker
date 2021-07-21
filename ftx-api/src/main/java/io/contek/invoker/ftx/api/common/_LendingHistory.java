package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _LendingHistory implements Serializable {

    public String coin;
    public double cost;
    public double rate;
    public double size;
    public String time;

    @Override
    public String toString() {
        return "_LendingHistory{" +
                "coin='" + coin + '\'' +
                ", cost=" + cost +
                ", rate=" + rate +
                ", size=" + size +
                ", time='" + time + '\'' +
                '}';
    }
}
