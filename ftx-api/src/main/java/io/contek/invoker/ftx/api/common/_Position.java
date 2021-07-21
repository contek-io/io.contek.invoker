package io.contek.invoker.ftx.api.common;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serializable;

@NotThreadSafe
public class _Position implements Serializable {

    public Double cost;
    public Double entryPrice;
    public Double estimatedLiquidationPrice;
    public String future;
    public Double initialMarginRequirement;
    public Double longOrderSize;
    public Double maintenanceMarginRequirement;
    public Double netSize;
    public Double openSize;
    public Double realizedPnl;
    public Double shortOrderSize;
    public String side;
    public Double size;
    public Double unrealizedPnl;
    public Double collateralUsed;
    public Double recentAverageOpenPrice;
    public Double recentBreakEvenPrice;
    public Double recentPnl;

    @Override
    public String toString() {
        return "_Position{" +
                "cost=" + cost +
                ", entryPrice=" + entryPrice +
                ", estimatedLiquidationPrice=" + estimatedLiquidationPrice +
                ", future='" + future + '\'' +
                ", initialMarginRequirement=" + initialMarginRequirement +
                ", longOrderSize=" + longOrderSize +
                ", maintenanceMarginRequirement=" + maintenanceMarginRequirement +
                ", netSize=" + netSize +
                ", openSize=" + openSize +
                ", realizedPnl=" + realizedPnl +
                ", shortOrderSize=" + shortOrderSize +
                ", side='" + side + '\'' +
                ", size=" + size +
                ", unrealizedPnl=" + unrealizedPnl +
                ", collateralUsed=" + collateralUsed +
                ", recentAverageOpenPrice=" + recentAverageOpenPrice +
                ", recentBreakEvenPrice=" + recentBreakEvenPrice +
                ", recentPnl=" + recentPnl +
                '}';
    }
}
