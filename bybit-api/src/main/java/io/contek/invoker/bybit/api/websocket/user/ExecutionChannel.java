package io.contek.invoker.bybit.api.websocket.user;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

@ThreadSafe
public final class ExecutionChannel extends WebSocketChannel<ExecutionChannel.Message> {

  ExecutionChannel() {
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
      super("execution");
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage<Data> {}

  @NotThreadSafe
  public static final class Data extends ArrayList<Execution> {}

  @NotThreadSafe
  public static final class Execution {

    public String category;
    public String symbol;
    public String execFee;
    public String execId;
    public String execPrice;
    public String execQty;
    public String execType;
    public String execValue;
    public Boolean isMaker;
    public String feeRate;
    public String tradeIv;
    public String markIv;
    public String blockTradeId;
    public String markPrice;
    public String indexPrice;
    public String underlyingPrice;
    public String leavesQty;
    public String orderId;
    public String orderLinkId;
    public String orderPrice;
    public String orderQty;
    public String orderType;
    public String stopOrderType;
    public String side;
    public String execTime;
    public String isLeverage;
    public String closedSize;
    public Long seq;
  }
}
