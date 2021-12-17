package io.contek.invoker.bybitlinear.api.websocket.user;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannel;
import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public final class OrderChannel extends WebSocketChannel<OrderChannel.Id, OrderChannel.Message> {

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
  public static final class Message extends WebSocketTopicMessage {

    public List<Data> data;
  }

  @NotThreadSafe
  public static final class Data {

    public String order_id;
    public String order_link_id;
    public String symbol;
    public String side;
    public String order_type;
    public String price;
    public int qty;
    public String time_in_force;
    public String create_type;
    public String cancel_type;
    public String order_status;
    public int leaves_qty;
    public int cum_exec_qty;
    public String cum_exec_value;
    public String cum_exec_fee;
    public String timestamp;
    public String take_profit;
    public String stop_loss;
    public String trailing_stop;
    public String last_exec_price;
    public boolean reduce_only;
    public boolean close_on_trigger;
  }
}
