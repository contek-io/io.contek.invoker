package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;
import java.util.List;

@NotThreadSafe
public class _AccountInformation implements Serializable {

    public Boolean backstopProvider;
    public Double collateral;
    public Double freeCollateral;
    public Double initialMarginRequirement;
    public Double leverage;
    public Boolean liquidating;
    public Double maintenanceMarginRequirement;
    public Double makerFee;
    public Double marginFraction;
    public Double openMarginFraction;
    public Double takerFee;
    public Double totalAccountValue;
    public Double totalPositionSize;
    public String username;
    public List<_Position> positions;

    @Override
    public String toString() {
        return "_AccountInformation{" +
                "backstopProvider=" + backstopProvider +
                ", collateral=" + collateral +
                ", freeCollateral=" + freeCollateral +
                ", initialMarginRequirement=" + initialMarginRequirement +
                ", leverage=" + leverage +
                ", liquidating=" + liquidating +
                ", maintenanceMarginRequirement=" + maintenanceMarginRequirement +
                ", makerFee=" + makerFee +
                ", marginFraction=" + marginFraction +
                ", openMarginFraction=" + openMarginFraction +
                ", takerFee=" + takerFee +
                ", totalAccountValue=" + totalAccountValue +
                ", totalPositionSize=" + totalPositionSize +
                ", username='" + username + '\'' +
                ", positions=" + positions +
                '}';
    }
}
