package io.contek.invoker.ftx.api.common;

import java.io.Serializable;

public class _LendingInfo implements Serializable {

    public String coin;
    public Double lendable;
    public Double locked;
    public Double minRate;
    public Double offered;

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
