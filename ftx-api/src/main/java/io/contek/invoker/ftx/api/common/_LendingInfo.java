package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _LendingInfo implements Serializable {

    public String coin;
    public double lendable;
    public double locked;
    public double minRate;
    public double offered;

    @Override
    public String toString() {
        return "_LendingInfo{" +
                "coin='" + coin + '\'' +
                ", lendable=" + lendable +
                ", locked=" + locked +
                ", minRate=" + minRate +
                ", offered=" + offered +
                '}';
    }
}
