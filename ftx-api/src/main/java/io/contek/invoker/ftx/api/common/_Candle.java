package io.contek.invoker.ftx.api.common;

public class _Candle {

    public double close;
    public double high;
    public double low;
    public double open;
    public String startTime;
    public double volume;

    @Override
    public String toString() {
        return "_Candle{" +
                "close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", startTime='" + startTime + '\'' +
                ", volume=" + volume +
                '}';
    }
}
