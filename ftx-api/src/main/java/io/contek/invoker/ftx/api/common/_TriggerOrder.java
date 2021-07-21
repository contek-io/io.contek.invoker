package io.contek.invoker.ftx.api.common;

public class _TriggerOrder {

    public String createdAt;
    public String future;
    public long id;
    public String market;
    public double triggerPrice;
    public Long orderId;
    public String side;
    public double size;
    public String status;
    public String type;
    public Double orderPrice;
    public Object error;
    public String triggeredAt;
    public boolean reduceOnly;
    public String orderType;
    public boolean retryUntilFilled;

    @Override
    public String toString() {
        return "_TriggerOrder{" +
                "createdAt='" + createdAt + '\'' +
                ", future='" + future + '\'' +
                ", id=" + id +
                ", market='" + market + '\'' +
                ", triggerPrice=" + triggerPrice +
                ", orderId=" + orderId +
                ", side='" + side + '\'' +
                ", size=" + size +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", orderPrice=" + orderPrice +
                ", error=" + error +
                ", triggeredAt='" + triggeredAt + '\'' +
                ", reduceOnly=" + reduceOnly +
                ", orderType='" + orderType + '\'' +
                ", retryUntilFilled=" + retryUntilFilled +
                '}';
    }
}
