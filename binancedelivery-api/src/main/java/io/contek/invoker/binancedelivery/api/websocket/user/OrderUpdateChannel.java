package io.contek.invoker.binancedelivery.api.websocket.user;

import io.contek.invoker.binancedelivery.api.websocket.common.WebSocketEventMessage;

import static io.contek.invoker.binancedelivery.api.websocket.user.constants.UserEventTypeKeys._ORDER_TRADE_UPDATE;

public final class OrderUpdateChannel
    extends UserWebSocketChannel<OrderUpdateChannel.Id, OrderUpdateChannel.Message> {

  OrderUpdateChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends UserWebSocketChannelId<Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_ORDER_TRADE_UPDATE);
    }
  }

  public static final class Message extends WebSocketEventMessage {

    public long T; // trasnaction time
    public Order o;

    @Override
    public String toString() {
      return "Message{" +
              "T=" + T +
              ", o=" + o +
              '}';
    }

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

      @Override
      public String toString() {
        return "Order{" +
                "s='" + s + '\'' +
                ", c='" + c + '\'' +
                ", S='" + S + '\'' +
                ", o='" + o + '\'' +
                ", f='" + f + '\'' +
                ", q=" + q +
                ", p=" + p +
                ", ap=" + ap +
                ", sp=" + sp +
                ", x='" + x + '\'' +
                ", X='" + X + '\'' +
                ", i=" + i +
                ", l=" + l +
                ", z=" + z +
                ", L=" + L +
                ", N='" + N + '\'' +
                ", n='" + n + '\'' +
                ", T=" + T +
                ", t=" + t +
                ", b=" + b +
                ", a=" + a +
                ", m=" + m +
                ", R=" + R +
                ", wt='" + wt + '\'' +
                ", ot='" + ot + '\'' +
                ", ps='" + ps + '\'' +
                ", cp=" + cp +
                ", AP=" + AP +
                ", cr=" + cr +
                ", rp=" + rp +
                '}';
      }
    }
  }
}
