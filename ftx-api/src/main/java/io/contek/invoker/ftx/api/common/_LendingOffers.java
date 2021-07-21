package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _LendingOffers implements Serializable {

    public String coin;
    public double rate;
    public double size;

    @Override
    public String toString() {
        return "_LendingOffers{" +
                "coin='" + coin + '\'' +
                ", rate=" + rate +
                ", size=" + size +
                '}';
    }
}
