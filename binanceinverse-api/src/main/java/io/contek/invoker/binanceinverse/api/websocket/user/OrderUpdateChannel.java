package io.contek.invoker.binanceinverse.api.websocket.user;

import io.contek.invoker.binanceinverse.api.websocket.common.WebSocketEventData;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.binanceinverse.api.websocket.user.constants.UserEventTypeKeys._ORDER_TRADE_UPDATE;

@ThreadSafe
public final class OrderUpdateChannel extends UserWebSocketChannel<OrderUpdateChannel.Data> {

  OrderUpdateChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Data> getMessageType() {
    return Data.class;
  }

  @Immutable
  public static final class Id extends UserWebSocketChannelId<Data> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_ORDER_TRADE_UPDATE);
    }
  }

  @NotThreadSafe
  public static final class Data extends WebSocketEventData {

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
