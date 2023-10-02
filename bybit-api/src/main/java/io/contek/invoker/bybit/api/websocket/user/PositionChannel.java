package io.contek.invoker.bybit.api.websocket.user;

import io.contek.invoker.bybit.api.websocket.WebSocketChannel;
import io.contek.invoker.bybit.api.websocket.WebSocketChannelId;
import io.contek.invoker.bybit.api.websocket.common.WebSocketTopicMessage;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;

@ThreadSafe
public final class PositionChannel extends WebSocketChannel<PositionChannel.Message> {

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
  public static final class Message extends WebSocketTopicMessage<Data> {}

  @NotThreadSafe
  public static final class Data extends ArrayList<Position> {}

  @NotThreadSafe
  public static final class Position {

    public Long user_id; // user ID
    public String symbol; // the contract for this position
    public Double size; // the current position amount
    public String side; // side
    public String position_value; // positional value
    public String entry_price; // entry price
    public String liq_price; // liquidation price
    public String bust_price; // bankruptcy price
    public String leverage; // leverage
    public String order_margin; // order margin
    public String position_margin; // position margin
    public String occ_closing_fee; // position closing
    public String take_profit; // take profit price
    public String
        tp_trigger_by; // take profit trigger price, eg: LastPrice, IndexPrice. Conditional order
    // only
    public String stop_loss; // stop loss price
    public String
        sl_trigger_by; // stop loss trigger price, eg: LastPrice, IndexPrice. Conditional order only
    public String realised_pnl; // realised PNL
    public String auto_add_margin; // Whether or not auto-margin replenishment is enabled
    public String cum_realised_pnl; // Total realized profit and loss
    public String position_status; // Position status: Normal, Liq, Adl
    public String position_seq; // position version number
    public Double
        free_qty; // Qty which can be closed. (If you have a long position, free_qty is negative.
    // vice versa)
    public String tp_sl_mode; // TrailingProfit or StopLoss mode Full or Partial
    public String risk_id; // Risk ID
    public Boolean isolated; // true means isolated margin mode; false means cross margin mode
    public String mode; // 	Position mode, MergedSingle or BothSide
    public String
        position_idx; // Position idx, used to identify positions in different position modes
  }
}
