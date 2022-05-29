package io.contek.invoker.ftx.api.websocket.user;

import io.contek.invoker.ftx.api.websocket.common.WebSocketChannelMessage;

import static io.contek.invoker.ftx.api.websocket.common.constants.WebSocketChannelKeys._fills;

public final class FillUpdateChannel
    extends WebSocketUserChannel<FillUpdateChannel.Id, FillUpdateChannel.Message> {

  FillUpdateChannel() {
    super(Id.INSTANCE);
  }

  @Override
  public Class<Message> getMessageType() {
    return FillUpdateChannel.Message.class;
  }

  public static final class Id extends WebSocketUserChannelId<FillUpdateChannel.Message> {

    private static final Id INSTANCE = new Id();

    private Id() {
      super(_fills);
    }
  }

  public static final class Data {
    public double fee;
    public double feeRate;
    public String future;
    public long id;
    public String liquidity;
    public String market;
    public long orderId;
    public long tradeId;
    public double price;
    public String side;
    public double size;
    public String time;
    public String type;

    @Override
    public String toString() {
      return "Data{"
          + "fee="
          + fee
          + ", feeRate="
          + feeRate
          + ", future='"
          + future
          + '\''
          + ", id="
          + id
          + ", liquidity='"
          + liquidity
          + '\''
          + ", market='"
          + market
          + '\''
          + ", orderId="
          + orderId
          + ", tradeId="
          + tradeId
          + ", price="
          + price
          + ", side='"
          + side
          + '\''
          + ", size="
          + size
          + ", time='"
          + time
          + '\''
          + ", type='"
          + type
          + '\''
          + '}';
    }
  }

  public static final class Message extends WebSocketChannelMessage<Data> {}
}
