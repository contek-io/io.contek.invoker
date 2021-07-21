package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

@NotThreadSafe
public class _Ticker implements Serializable {

    public Double bid;
    public Double ask;
    public Double askSize;
    public Double bidSize;
    public Double last;
    public Double time;

    @Override
    public String toString() {
        return "_Ticker{" +
                "bid=" + bid +
                ", ask=" + ask +
                ", askSize=" + askSize +
                ", bidSize=" + bidSize +
                ", last=" + last +
                ", time=" + time +
                '}';
    }
}
