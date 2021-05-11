package io.contek.invoker.binancefutures.api.websocket.user;

import io.contek.invoker.binancefutures.api.websocket.common.WebSocketEventMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binancefutures.api.websocket.user.constants.UserEventTypeKeys._MARGIN_CALL;

@ThreadSafe
public final class OrderUpdateChannel
    extends UserWebSocketChannel<OrderUpdateChannel.Id, OrderUpdateChannel.Message> {

  OrderUpdateChannel() {
    super(Id.INSTANCE);
  }

  @Override
  protected Class<OrderUpdateChannel.Message> getMessageType() {
    return OrderUpdateChannel.Message.class;
  }

  @Immutable
  public static final class Id extends UserWebSocketChannelId<OrderUpdateChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_MARGIN_CALL);
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketEventMessage {

    public long T; // trasnaction time
    public Order o;

    @NotThreadSafe
    public static class Order {

      public String s; // symbol
      public String c; // client order id
      public String S; // side
      public String o; // order type
      public String f; // time in force
      public Double q; // original quantity;
      public Double p; // original price
      public Double ap; // average price
      public Double sp; // stop price
      public String x; // execution type
      public String X; // order status
      public Long i; // order id
      public Double l; // order last filled quantity
      public Double z; // order filled accumulated quantity
      public Double L; // last filled quantity
      public String N; // commission asset
      public String n; // commission
      public Long T; // order trade time
      public Long t; // trade id
      public Double b; // bids notional
      public Double a; // ask notional
      public Boolean m; // is the trade the maker side
      public Boolean R; // is this trade reduce only
      public String wt; // stop work price type
      public String ot; // original order type
      public String ps; // position side
      public Boolean cp; // if close all push with conditional order
      public Double AP; // activatin price
      public Double cr; // callback rate
      public Double rp; // realized profit
    }
  }
}
