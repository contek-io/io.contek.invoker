package io.contek.invoker.ftx.api.common;

public class _FutureStats {

    public Double volume;
    public Float nextFundingRate;
    public String nextFundingTime;
    public Double expirationPrice;
    public Double predictedExpirationPrice;
    public Double strikePrice;
    public Double openInterest;

    @Override
    public String toString() {
        return "_FutureStats{" +
                "volume=" + volume +
                ", nextFundingRate=" + nextFundingRate +
                ", nextFundingTime='" + nextFundingTime + '\'' +
                ", expirationPrice=" + expirationPrice +
                ", predictedExpirationPrice=" + predictedExpirationPrice +
                ", strikePrice=" + strikePrice +
                ", openInterest=" + openInterest +
                '}';
    }
}
