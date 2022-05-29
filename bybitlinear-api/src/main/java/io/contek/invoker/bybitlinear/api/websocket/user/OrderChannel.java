package io.contek.invoker.bybitlinear.api.websocket.user;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannel;
import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;

import java.util.List;

public final class OrderChannel extends WebSocketChannel<OrderChannel.Id, OrderChannel.Message> {

  OrderChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return Message.class;
  }

  public static final class Id extends WebSocketChannelId<Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super("order");
    }
  }

  public static final class Message extends WebSocketTopicMessage {

    public List<Data> data;
  }

  public static final class Data {

    public String order_id;
    public String order_link_id;
    public String symbol;
    public String side;
    public String order_type;
    public String price;
    public Double qty;
    public Double leaves_qty;
    public String last_exec_price;
    public Double cum_exec_qty;
    public String cum_exec_value;
    public String cum_exec_fee;
    public String time_in_force;
    public String create_type;
    public String cancel_type;
    public String order_status;
    public String take_profit;
    public String stop_loss;
    public String trailing_stop;
    public Boolean reduce_only;
    public Boolean close_on_trigger;
    public String create_time;
    public String update_time;
  }
}
