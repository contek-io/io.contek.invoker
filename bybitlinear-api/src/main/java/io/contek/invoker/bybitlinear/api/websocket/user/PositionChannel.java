package io.contek.invoker.bybitlinear.api.websocket.user;

import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannel;
import io.contek.invoker.bybitlinear.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybitlinear.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.List;

@ThreadSafe
public final class PositionChannel
    extends WebSocketChannel<PositionChannel.Id, PositionChannel.Message> {

  PositionChannel() {
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
      super("position");
    }
  }

  @NotThreadSafe
  public static final class Message extends WebSocketTopicMessage {

    public List<Data> data;
  }

  @NotThreadSafe
  public static final class Data {

    public Long user_id; // user ID
    public String symbol; // the contract for this position
    public Integer size; // the current position amount
    public String side; // side
    public String position_value; // positional value
    public String entry_price; // entry price
    public String liq_price; // liquidation price
    public String bust_price; // bankruptcy price
    public String leverage; // leverage
    public String order_margin; // order margin
    public String position_margin; // position margin
    public String available_balance; // available balance
    public String take_profit; // take profit price
    public String
        tp_trigger_by; // take profit trigger price, eg: LastPrice, IndexPrice. Conditional order
    // only
    public String stop_loss; // stop loss price
    public String
        sl_trigger_by; // stop loss trigger price, eg: LastPrice, IndexPrice. Conditional order only
    public String realised_pnl; // realised PNL
    public String trailing_stop; // trailing stop points
    public String trailing_active; // trailing stop trigger price
    public String wallet_balance; // wallet balance
    public Integer risk_id;
    public String occ_closing_fee; // position closing
    public String occ_funding_fee; // funding fee
    public Integer auto_add_margin; // auto margin replenishment switch
    public String cum_realised_pnl; // Total realized profit and loss
    public String
        position_status; // status of position (Normal: normal Liq: in the process of liquidation
    // Adl: in the process of Auto-Deleveraging)
    public Long position_seq; // position version number
  }
}
