package io.contek.invoker.bybit.api.websocket.user;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

@ThreadSafe
public final class OrderChannel extends WebSocketChannel<OrderChannel.Message> {

  OrderChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  @Immutable
  public static final class Id extends WebSocketChannelId<Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super("order");
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketUserMessage<Data> {}

  @NotThreadSafe
  public static final class Data extends ArrayList<Order> {}

  @NotThreadSafe
  public static final class Order {

    public String symbol;
    public String orderId;
    public String side;
    public String orderType;
    public String cancelType;
    public String price;
    public String qty;
    public String orderIv;
    public String timeInForce;
    public String orderStatus;
    public String orderLinkId;
    public String lastPriceOnCreated;
    public Boolean reduceOnly;
    public String leavesQty;
    public String leavesValue;
    public String cumExecQty;
    public String cumExecValue;
    public String avgPrice;
    public String blockTradeId;
    public Integer positionIdx;
    public String cumExecFee;
    public String createdTime;
    public String updatedTime;
    public String rejectReason;
    public String stopOrderType;
    public String tpslMode;
    public String triggerPrice;
    public String takeProfit;
    public String stopLoss;
    public String tpTriggerBy;
    public String slTriggerBy;
    public String tpLimitPrice;
    public String slLimitPrice;
    public Integer triggerDirection;
    public String triggerBy;
    public Boolean closeOnTrigger;
    public String category;
    public String placeType;
    public String smpType;
    public Integer smpGroup;
    public String smpOrderId;
    public String feeCurrency;
  }
}
