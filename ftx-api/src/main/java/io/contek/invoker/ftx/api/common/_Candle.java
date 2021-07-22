package io.contek.invoker.ftx.api.common;

public class _Candle {

    public Double close;
    public Double high;
    public Double low;
    public Double open;
    public String startTime;
    public Double volume;

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
